package BatiCuisine.Services;

import BatiCuisine.Entities.Material;
import BatiCuisine.Repository.Implementation.MaterialRepository;

public class MaterialService {
    private MaterialRepository materialRepository;

    public MaterialService() {
        this.materialRepository = materialRepository;
    }
    public Material save(Material material){
        return materialRepository.save(material);
    }
}
