package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Button;

public class MultiToggle extends Button {
    private int m_value;
    private Button m_button;
    private boolean m_pressedLast;
    private int m_pressCount = 0 ;
    

    MultiToggle (Button button, int value){
        m_button = button;
        m_value = value;
    }

    public boolean get(){
        if (!m_pressedLast && m_button.get()){
            m_pressCount++;
        }
        m_pressedLast=m_button.get();
        if (m_pressCount > 3) {
            m_pressCount = 0;
        }
        return (m_pressCount == m_value);
    }
    
}
