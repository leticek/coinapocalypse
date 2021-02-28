/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.managers;

import core.objects.CollisionObject;
import core.objects.MovableObject;
import core.objects.MyObject;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class ObjectManager {

    private ArrayList<MyObject> objectArrayList;

    /**
     * Konstruktor ObjectManager, vytvoří nový ArrayList
     */
    public ObjectManager() {
        this.objectArrayList = new ArrayList<>();
    }

    /**
     * Přídá MovableObject do ArrayListu
     *
     * @param object MovableObject, který má být přidán do ArrayListu
     */
    public void addMovableObject(MovableObject object) {
        objectArrayList.add(object);
    }

    /**
     * Přidá CollisionObject do ArrayListu
     *
     * @param object CollisionObject, který má být přidán do ArrayListu
     */
    public void addCollisionObject(CollisionObject object) {
        objectArrayList.add(object);
    }

    /**
     * Projde objectArrayList a pro každý prvek přidá jeho ImageView
     *
     * @return Vrací ArrayList ImageView každého objektu
     */
    public ArrayList<ImageView> getImageViewArrayList() {
        ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
        for (MyObject object : this.objectArrayList) {
            imageViewArrayList.add(object.getTexture());
        }
        return imageViewArrayList;
    }

    /**
     * @return ArrayList s objecty
     */
    public ArrayList<MyObject> getObjectArrayList() {
        return this.objectArrayList;
    }

}
