package dao.inter;

import java.util.ArrayList;
import model.Material;

public interface IMaterialDAO {

    public ArrayList<Material> list();

    public Material getMaterialById(int id);
    
    public void update(Material model);
    
     public void delete(int id);
     
     public ArrayList<Material> searchByName(String name);
     
     public void add(String name, int quantity, int broken_quantity);
}
