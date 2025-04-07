package gui.gridControllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Steuerung des GridPanes in "<BorderPane> <left>".
 * Die Klasse erbt von Controller.
 *
 * @author Maurice Singh Multani
 */
public class GridLeftController extends Controller {

    /**
     * Konstruktor, welches ein GridLeftController mit einem GridPane initialisiert.
     *
     * @param gridPane das GridPane, was mit dem GridLeftController initialisiert wird.
     */
    public GridLeftController(GridPane gridPane){
        super(gridPane);
    }

    @Override
    public String[] images() {
        return new String[]{
                "/gui/tiles/GGGG.png",
                "/gui/tiles/GGRR.png",
                "/gui/tiles/GRGR.png",
                "/gui/tiles/GRRR.png",
                "/gui/tiles/GRYG.png",
                "/gui/tiles/GRYR.png",
                "/gui/tiles/GYRG.png",
                "/gui/tiles/GYYY.png",
        };
    }

    public void initImages() {
        final int rowsCount = gridPane.getRowCount();

        ImageView[] imageViews = new ImageView[rowsCount];

        for (int i = 0; i < images().length; i++) {
            imageViews[i] = new ImageView();

            Image img = new Image(getClass().getResourceAsStream(images()[i]));
            imageViews[i].setImage(img);

            gridPane.add(imageViews[i], 0, i);

            imageViews[i].fitWidthProperty().bind(gridPane.widthProperty());
            imageViews[i].fitHeightProperty().bind(gridPane.heightProperty().divide(rowsCount));
        }

    }
}
