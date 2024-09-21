package BatiCuisine.Services;

import BatiCuisine.Entities.Material;
import BatiCuisine.Repository.Implementation.MaterialRepository;

import java.util.List;
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
    public List<Material> findAll(){
        return materialRepository.findAll();
    }
    public Material update(Material material){
        return materialRepository.update(material);
    }
    public Boolean delete(int id){
        return materialRepository.delete(id);
    }

}
