package gui.gridControllers;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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

        for (int row = 0; row < images().length; row++) {
            Image img = new Image(getClass().getResourceAsStream(images()[row]));

            ImageView imageView = new ImageView(img);

            imageView.fitWidthProperty().bind(gridPane.widthProperty());
            imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(rowsCount));
            imageView.setPreserveRatio(true);

            Label tile = new Label();
            tile.setGraphic(imageView);

            // Drag starten
            tile.setOnDragDetected(mouseEvent -> {
                Dragboard db = tile.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(img);
                db.setContent(content);
                mouseEvent.consume();
            });

            gridPane.add(tile, 0, row);
        }
    }
}
