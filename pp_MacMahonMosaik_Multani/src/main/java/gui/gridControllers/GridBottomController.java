package gui.gridControllers;

import gui.TileActions;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.Rotation;

/**
 * Steuerung des GridPanes in "<BorderPane> <bottom>".
 * Die Klasse erbt von Controller.
 *
 * @author Maurice Singh Multani
 */
public class GridBottomController extends Controller {

    /**
     * Konstruktor, welches ein GridBottomController mit einem GridPane initialisiert.
     *
     * @param gridPane das GridPane, was mit dem GridBottomController initialisiert wird.
     */
    public GridBottomController(GridPane gridPane){super(gridPane);}

    @Override
    public String[] images() {
        return new String[]{
                "/gui/tiles/RGGG.png",
                "/gui/tiles/RGYG.png",
                "/gui/tiles/RGYR.png",
                "/gui/tiles/RRRR.png",
                "/gui/tiles/RRYY.png",
                "/gui/tiles/RYGR.png",
                "/gui/tiles/RYGY.png",
                "/gui/tiles/RYYY.png",
        };
    }

    @Override
    public void initImages() {
        final int columnCount = gridPane.getColumnCount();

        for (int bottom = 0; bottom < images().length; bottom++) {
            Image img = new Image(getClass().getResourceAsStream(images()[bottom]));

            ImageView imageView = new ImageView(img);

            imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
            imageView.fitHeightProperty().bind(gridPane.heightProperty());
            imageView.setPreserveRatio(true);

            Label tile = new Label();
            tile.setGraphic(imageView);
            tile.setUserData(Rotation.DEGREE_0);

            // Drag starten
            TileActions.dragTiles(gridPane, tile, imageView);

            gridPane.add(tile, bottom, 0);
        }
    }
}