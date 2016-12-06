import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Models a mouse adapter listener.
 * This class was adopted from Dr.Kim's lecture note.
 */
public abstract class MouseAdapter implements MouseListener, MouseWheelListener, MouseMotionListener
{
	public void mouseClicked(MouseEvent e){};
	public void mouseDragged(MouseEvent e){}; 
	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){}; 
	public void mouseMoved(MouseEvent e){}; 
	public void mousePressed(MouseEvent e){}; 
	public void mouseReleased(MouseEvent e){}; 
	public void mouseWheelMoved(MouseWheelEvent e) {};
}