package gui.gridControllers;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

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

        ImageView[] imageViews = new ImageView[columnCount];

        for (int i = 0; i < images().length; i++) {
            imageViews[i] = new ImageView();

            String imagePath = images()[i];

            Image img = new Image(getClass().getResourceAsStream(imagePath));
            imageViews[i].setImage(img);

            gridPane.add(imageViews[i], i, 0);

            imageViews[i].fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
            imageViews[i].fitHeightProperty().bind(gridPane.heightProperty());
        }
    }

    @Override
    /**
     * Ziehen der Mosaikteile.
     */
    public void dragTiles(){
        for (String path : images()) {

            Image image = new Image(path);
            ImageView imageView = new ImageView(image);
            // Anpassung der Größe fehlt noch

            Label tile = new Label();
            tile.setGraphic(imageView);

            tile.setOnDragDetected(mouseEvent -> {
                Dragboard db = tile.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(image);
                db.setContent(content);
                mouseEvent.consume();
            });

            gridPane.add(tile, 0, 0);


        }
    }
}
