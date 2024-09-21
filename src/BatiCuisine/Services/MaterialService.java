package BatiCuisine.Services;

import BatiCuisine.Entities.Material;
import BatiCuisine.Repository.Implementation.MaterialRepository;

import java.util.Optional;

public class MaterialService {
    private MaterialRepository materialRepository;

    public MaterialService() {
        this.materialRepository = materialRepository;
    }
    public Material save(Material material){
        return materialRepository.save(material);
    }
    public Optional<Material> findById(int id){
        return materialRepository.findById(id);
    }
}
