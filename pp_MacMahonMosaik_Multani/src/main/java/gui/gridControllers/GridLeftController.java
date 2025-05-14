package gui.gridControllers;

import gui.TileActions;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.Rotation;

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
    public GridLeftController(GridPane gridPane) {
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

        for (int row = 0; row < images().length; row++) {
            Image img = new Image(getClass().getResourceAsStream(images()[row]));

            ImageView imageView = new ImageView(img);
            imageView.fitWidthProperty().bind(gridPane.widthProperty());
            imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(rowsCount));
            imageView.setPreserveRatio(true);

            Label tile = new Label();
            tile.setGraphic(imageView);
            tile.setUserData(Rotation.DEGREE_0);

            // Drag starten
            TileActions.dragTiles(gridPane, tile, imageView);

            gridPane.add(tile, 0, row);
        }
    }
}