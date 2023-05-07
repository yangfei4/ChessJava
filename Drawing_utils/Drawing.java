import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import java.util.ArrayList;

class Circle{
    int x, y;
    int radius;

    public Circle(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}

public class Drawing extends JFrame{
    private JPanel canvas;
    private List<Rectangle> rectangles;
    private List<Circle> circles;

    private Rectangle currentRec;
    private Circle currentCir;

    private JButton RecButton;
    private JButton CirButton;

    // "rec" or "cir"
    private String current_graphic;

    private int width = 800, height = 800;

    public Drawing(){
        // Initialize canvas: method from JFrame
        setTitle("Drawing Example");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rectangles = new ArrayList<>();
        circles = new ArrayList<>();
        current_graphic = "rec";
        
        // Add button
        RecButton = new JButton("Rectangle");
        CirButton = new JButton("Circle");
        // Button event, e is ActionEvent instance
        RecButton.addActionListener(e -> current_graphic="rec");
        CirButton.addActionListener(e -> current_graphic="cir");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(RecButton);
        buttonPanel.add(CirButton);
        add(buttonPanel, BorderLayout.NORTH);

        canvas = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                for(Rectangle rec:rectangles){
                    g.drawRect(rec.x, rec.y, rec.width, rec.height);
                }

                for(Circle cir:circles){
                    g.drawOval(cir.x-cir.radius, cir.y-cir.radius, 2*cir.radius, 2*cir.radius);
                }

                // drawing current rectangle in real time
                if(currentRec != null)
                    g.drawRect(currentRec.x, currentRec.y, currentRec.width, currentRec.height);
                if(currentCir != null)
                    g.drawOval(currentCir.x-currentCir.radius, currentCir.y-currentCir.radius, 
                               2*currentCir.radius, 2*currentCir.radius);

            }
        };

        add(canvas);

        // Set the mouse listeners for the canvas panel
        // MouseAdapter for receiving mouse event
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                super.mousePressed(e);

                if(current_graphic=="rec")
                    currentRec = new Rectangle(e.getX(), e.getY(), 0, 0);
                else if(current_graphic=="cir")
                    currentCir = new Circle(e.getX(), e.getY(), 0);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e){
                if(current_graphic=="rec"){
                    rectangles.add(currentRec);
                    currentRec = null;
                }
                else if(current_graphic=="cir"){
                    circles.add(currentCir);
                    currentCir = null;
                }

                repaint();
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                if(currentRec != null){
                    currentRec.width = e.getX() - currentRec.x;
                    currentRec.height = e.getY() - currentRec.y;
                    repaint();
                }
                if(currentCir != null){
                    double square = Math.pow((e.getX()-currentCir.x), 2) + Math.pow((e.getY()-currentCir.y), 2);
                    // currentCir.radius = (int) Math.sqrt(square);
                    currentCir.radius = Math.max(e.getX()-currentCir.x, e.getY()-currentCir.y);
                    repaint();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(Drawing::new);
    }

}