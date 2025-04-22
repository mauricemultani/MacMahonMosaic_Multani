package gui.gridControllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Steuerung des GridPanes in "<BorderPane> <right>".
 * Die Klasse erbt von Controller.
 *
 * @author Maurice Singh Multani
 */
public class GridRightController extends Controller {

    /**
     * Konstruktor, welches ein GridRightController mit einem GridPane initialisiert.
     *
     * @param gridPane das GridPane, was mit dem GridRightController initialisiert wird.
     */
    public GridRightController(GridPane gridPane){
        super(gridPane);
    }

    @Override
    public String[] images() {
        return new String[]{
                "/gui/tiles/YGGG.png",
                "/gui/tiles/YGRY.png",
                "/gui/tiles/YGYG.png",
                "/gui/tiles/YRGY.png",
                "/gui/tiles/YRRR.png",
                "/gui/tiles/YRYR.png",
                "/gui/tiles/YYGG.png",
                "/gui/tiles/YYYY.png",
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

    @Override
    public void dragTiles() {

    }
}
