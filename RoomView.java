import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model a room view Panel.
 * @author DuocNguyen
 *
 */
public class RoomView extends JPanel{
    private JPanel rv;
    private JTextArea infoArea;
    private ArrayList<Room> resRoom;
    private RoomDataModel rmodel;
    private String savedRoomsInfo;
    private ArrayList<JLabel> rLabels;
    
    /**
     * Constructs a room view panel.
     * @param hm
     */
    public RoomView(HotelModel hm){
        savedRoomsInfo = "";
        rmodel = new RoomDataModel();
        resRoom = new ArrayList<Room>();
        rLabels = new ArrayList<>();
        setPreferredSize(new Dimension(390, 465));
        this.setBorder(LineBorder.createGrayLineBorder());
        this.setLayout(new BorderLayout());
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(460, 50));
        buttons.setBorder(LineBorder.createGrayLineBorder());
        buttons.setLayout(new GridLayout(1,2));
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new
                                     ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNum = rmodel.getData();
                savedRoomsInfo += getSelectedRoomInfo(roomNum);
            }
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new
                                     ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent event) {
                String fileName = "reservedRooms.txt";
                File file = new File(fileName);
                try {
                    PrintWriter writer = new PrintWriter(file, "UTF-8");
                    writer.println(savedRoomsInfo);
                    writer.close();
                    
                } catch (FileNotFoundException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hm.update(new WelcomePanel(hm));
            }
            
        });
        
        //add buttons to buttons panel
        buttons.add(saveButton);
        buttons.add(quitButton);
        
        add(buttons, BorderLayout.SOUTH);
        
        
        updatePanel(0);
        
        
        ChangeListener listener = new
        ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                int rNum = rmodel.getData();	//room number
                String s = getSelectedRoomInfo(rNum);
                infoArea.setText(s);
                for(int i = 0; i< 20; i++){
                    if(rNum == (Integer.parseInt(rLabels.get(i).getText()))){
                        rLabels.get(i).setBackground(Color.YELLOW);
                        rLabels.get(i).setOpaque(true);
                    } else {
                        rLabels.get(i).setBackground(null);
                    }
                }
            }
            
        };
        rmodel.attach(listener);
    }
    
    public void updatePanel(int rNum) {
        rv = new JPanel();
        rv.setBorder(LineBorder.createGrayLineBorder());	//adds gray lines
        rv.setLayout(new GridLayout(2,1));
        
        
        JPanel rooms = new JPanel();
        rooms.setBorder(LineBorder.createGrayLineBorder());
        rooms.setLayout(new GridLayout(4,5));
        for(int i = 1; i <= 20; i++){
            JLabel l = new JLabel(i+"", SwingConstants.CENTER);
            l.addMouseListener(new
                               MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    Point mousePoint = e.getPoint();
                    if(l.contains(mousePoint)) {
                        rmodel.update(Integer.parseInt(l.getText()));
                    }
                }
            });
            //adds event handling here. Whenever a room number is clicked. It shows the room info.
            l.setBorder(LineBorder.createGrayLineBorder());
            rooms.add(l);
            rLabels.add(l);
        }
        
        
        //display room info here
        JPanel selectedRoomInfo = new JPanel();
        selectedRoomInfo.setLayout(new BorderLayout());
        selectedRoomInfo.setBorder(LineBorder.createGrayLineBorder());
        JLabel header = new JLabel("Room Information", SwingConstants.CENTER);
        
        
        JPanel roomInfo = new JPanel();
        roomInfo.setBorder(LineBorder.createGrayLineBorder());
        JPanel container = new JPanel();
        infoArea = new JTextArea(32,32);
        infoArea.setEditable(false);
        container.add(infoArea);
        
        
        selectedRoomInfo.add(header, BorderLayout.NORTH);
        selectedRoomInfo.add(container);
        
        
        
        rv.add(rooms);
        rv.add(selectedRoomInfo);
        add(rv);
    }
    
    /**
     * Gets the reserved room information. 
     * @param roomNum	The selected room number.
     * @return	A string of information.
     */
    public String getSelectedRoomInfo(int roomNum){
        if(roomNum == 0){	//reset
            return "";
        }
        String currentRoomInfo = "";
        for(Room r: resRoom){
            if(r.getRoomNumber() == roomNum){
                Guest g = r.getGuest();
                currentRoomInfo += "Name: " + g.getUsername();
                currentRoomInfo += "\nUser ID: " + g.getUserID();
                currentRoomInfo += "\nReserved Room " + r.getRoomNumber() + ": " + r.getRoomType();
                currentRoomInfo += "\nStart Date: " + ReservationPanel.formatDate(r.getStartDate());
                currentRoomInfo += "\nEnd Date: " + ReservationPanel.formatDate(r.getEndDate()) + "\n\n";
            }
        }
        return currentRoomInfo;
    }
    
    /**
     * Sets the list of rooms to a list of guest reserved rooms.
     * @param r List of guest reserved rooms on the selected day.
     */
    public void setReservedRooms(ArrayList<Room> r){
        resRoom = r;
    }
    
    /**
     * Reset the room view to no selected room.
     * @postcondition: no room number is highlighted in room view.
     */
    public void reset(){
        rmodel.update(0);
    }
    
}
