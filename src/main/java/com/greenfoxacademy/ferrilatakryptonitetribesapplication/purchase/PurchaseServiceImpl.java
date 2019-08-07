package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Academy;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingFactory;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingType;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.TownHall;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private BuildingServiceImpl buildingService;
  private TroopServiceImpl troopService;
  private ResourceServiceImpl resourceService;
  private IKingdomRepository kingdomRepository;
  private KingdomServiceImpl kingdomService;
  private BuildingRepository buildingRepository;
  private TroopRepository troopRepository;

  private Long troopCreateCost = 10L;
  private Long buildingCreateCost = 100L;
  private int buildingUpgradeCost = 25;

  @Autowired
  public PurchaseServiceImpl(
      BuildingServiceImpl buildingService,
      TroopServiceImpl troopService,
      ResourceServiceImpl resourceService,
      IKingdomRepository kingdomRepository,
      KingdomServiceImpl kingdomService,
      BuildingRepository buildingRepository,
      TroopRepository troopRepository) {
    this.buildingService = buildingService;
    this.troopService = troopService;
    this.resourceService = resourceService;
    this.kingdomRepository = kingdomRepository;
    this.kingdomService = kingdomService;
    this.buildingRepository = buildingRepository;
    this.troopRepository = troopRepository;
  }

  @Override
  public int purchaseBuilding(Kingdom kingdom, BuildingDTO buildingDTO) {
    if (buildingDTO.getLevel() < 0) {
      throw new BuildingRelatedException("Building level can not be negative.");
    }

    if (buildingDTO.getType().equals("Townhall")) {
      throw new BuildingRelatedException("You can not create another townhall");
    }
    List<Resource> kingdomResources = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResources);
    purchaseBuildingIfEnoughGold(kingdom, buildingDTO, gold);
    return gold.getAmount();
  }

  @Override
  public int purchaseBuildingUpgrade(Kingdom kingdom, Long buildingId, Long upgradeLevelTo)
      throws Exception {
    Building building = buildingService.findBuildingById(buildingId);
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResource);
    if (building.getBuildingType() != BuildingType.TownHall) {
      building.setLevel(Math.min(upgradeLevelTo, townHallLevel(kingdom)));
      return purchaseIfEnoughGold(gold, upgradeLevelTo, buildingCreateCost);
    } else if (building.getLevel() < 10L) {
      building.setLevel(upgradeLevelTo);
      return purchaseIfEnoughGold(gold, upgradeLevelTo, buildingCreateCost);
    }
    return gold.getAmount();
  }

  @Override
  public int purchaseBuildingIfEnoughGold(Kingdom kingdom, BuildingDTO buildingDTO, Gold gold) {
    if (isGoldEnough(gold, buildingCreateCost * (buildingDTO.getLevel() + 1))) {
      if (buildingDTO.getLevel() <= kingdom.getBuildings().get(3).getLevel()) {
        long newGoldAmount = gold.getAmount() - (buildingCreateCost * (buildingDTO.getLevel() + 1));
        gold.setAmount((int) newGoldAmount);
        resourceService.saveResource(gold);
        buildingToSaveInit(buildingDTO, kingdom);
        return gold.getAmount();
      } else {
        throw new KingdomRelatedException("Building level higher than Town Hall level.");
      }
    } else {
      throw new ResourceRelatedException("Not enough gold to purchase.");
    }
  }


  @Transactional
  @Override
  public ResponseEntity purchaseTroop(Kingdom kingdom) {
    List<Resource> kingdomResources = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResources);
    if (isGoldEnough(gold, 10L)) {
      if (kingdom.getBuildings()
          .stream()
          .filter(p -> p instanceof Academy)
          .count() > 0) {
        troopService.createTroop(kingdom);
        purchaseIfEnoughGold(gold, 1L, troopCreateCost);
        return new ResponseEntity("Troop created, gold left: " + gold.getAmount(),
            HttpStatus.OK);
      } else {
        throw new BuildingRelatedException("Kingdom has no Academy to train troops.");
      }
    } else {
      throw new ResourceRelatedException("Not enough gold to purchase troop.");
    }
  }

  @Override
  public String purchaseTroopUpgrade(Kingdom kingdom, Long troopId, Long upgradeLevelTo)
      throws ResourceRelatedException {
    Troop troop = troopService.findTroopById(troopId);
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = getGoldOfKingdom(kingdomResource);
    if (troop.getLevel() < 3) {
      troop.setLevel(upgradeLevelTo);
      purchaseIfEnoughGold(gold, upgradeLevelTo, troopCreateCost);
      return "Troop upgraded, level: " + troop.getLevel() + ", HP: " + troop.getHp()
          + ", Attack: " + troop.getAttack() + ", Defense: " + troop.getDefense() + ".";
    } else {
      throw new ResourceRelatedException("Upgrade is not successful.");
    }
  }

  @Override
  public Boolean isGoldEnough(Gold gold, Long cost) {
    return gold.getAmount() >= cost;
  }

  @Override
  public Gold getGoldOfKingdom(List<Resource> kingdomResources) {
    List<Resource> filteredResources =
        kingdomResources.stream().filter(g -> g instanceof Gold).collect(Collectors.toList());
    return (Gold) filteredResources.get(0);
  }

  @Override
  public int purchaseIfEnoughGold(Gold gold, Long upgradeLevelTo, Long upgradeCost) {
    if (isGoldEnough(gold, upgradeCost)) {
      long newGoldAmount = gold.getAmount() - (upgradeLevelTo * upgradeCost);
      gold.setAmount((int) newGoldAmount);
      resourceService.saveResource(gold);
      return gold.getAmount();
    } else {
      throw new ResourceRelatedException("Not enough gold to purchase.");
    }
  }

  @Override
  public String constructNewBuilding(BuildingDTO buildingDTO) {
    if (buildingDTO == null) {
      throw new BuildingRelatedException("Missing type, level, kingdomId parameters");
    }
    if (buildingDTO.getType() == null || buildingDTO.getType().equals("")) {
      throw new BuildingRelatedException("Building type was not provided");
    }
    if (!(buildingDTO.getType().equals("Mine") || buildingDTO.getType().equals("Academy")
        || buildingDTO.getType().equals("Farm"))) {
      throw new BuildingRelatedException(buildingDTO.getType() + " is not a valid building type");
    }
    if (kingdomService.findKingdomById(buildingDTO.getKingdomId()) != null) {
      return "Successful construction, gold left: " + purchaseBuilding(
          kingdomService.findKingdomById(buildingDTO.getKingdomId()), buildingDTO);
    } else {
      throw new KingdomRelatedException("No kingdom exists with this id.");
    }
  }

  @Override
  public void buildingToSaveInit(BuildingDTO buildingDTO, Kingdom kingdom) {
    Building buildingToSave = BuildingFactory
        .createBuilding(BuildingType.valueOf(buildingDTO.getType()));
    buildingToSave.setBuildingType(BuildingType.valueOf(buildingDTO.getType()));
    buildingToSave.setLevel(buildingDTO.getLevel());
    buildingToSave.setKingdom(kingdom);
    buildingService.saveBuilding(buildingToSave);
    kingdom.getBuildings().add(buildingToSave);
    kingdomRepository.save(kingdom);
  }

  @Transactional
  @Override
  public Building upgradeBuildingByOneLevel(long buildingId) {
    Building building = buildingRepository.findBuildingById(buildingId);
    Kingdom kingdom = building.getKingdom();
    List<Resource> resources = kingdom.getResourceList();
    if (building instanceof TownHall
        || kingdom.getBuildings().get(3).getLevel() >= building.getLevel() + 1) {
      if (resources.get(0).getAmount() >= buildingUpgradeCost) {
        executeBuildingUpgrade(building, buildingId, resources, kingdom);
      } else {
        throw new ResourceRelatedException("Insufficient gold");
      }
    } else {
      throw new BuildingRelatedException("Upgrade is not allowed");
    }
    return building;
  }

  @Override
  public int findBuildingIndexByBuildingId(long buildingId, Kingdom kingdom) {
    for (int i = 0; i < kingdom.getBuildings().size(); i++) {
      if (kingdom.getBuildings().get(i).getId() == buildingId) {
        return i;
      }
    }
    return 0;
  }

  @Override
  public void executeBuildingUpgrade(Building building, long buildingId, List<Resource> resources,
      Kingdom kingdom) {
    building.setLevel(building.getLevel() + 1);
    buildingRepository.save(building);
    List<Building> myBuildings = kingdom.getBuildings();
    myBuildings.set(findBuildingIndexByBuildingId(buildingId, kingdom), building);
    Resource myGold = resources.get(0);
    myGold.setAmount(resources.get(0).getAmount() - buildingUpgradeCost);
    resources.set(0, myGold);
    resourceService.saveResource(myGold);
    kingdom.setResourceList(resources);
    kingdom.setBuildings(myBuildings);
    kingdomRepository.save(kingdom);
  }

  public long townHallLevel(Kingdom kingdom) {
    Building townhHall = new TownHall();
    for (Building building : kingdom.getBuildings()) {
      if (building.getBuildingType() == BuildingType.TownHall) {
        townhHall = building;
      }
    }
    return townhHall.getLevel();
  }
}
