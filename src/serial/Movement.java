package serial;

import java.io.Serializable;

/**
 * Created by CamiloMontoya on 1/03/17.
 */

public class Movement implements Serializable {
    public String move;

    public Movement(String move){
        this.move=move;
    }

    public String getContenido(){
        return move;
    }
    
    public void setContenido(){
        this.move=move;
    }
}