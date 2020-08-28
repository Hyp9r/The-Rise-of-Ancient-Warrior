package com.mygdx.GUI;

import java.util.ArrayList;
import java.util.List;

public abstract class Component{
    List<Component> components = new ArrayList<Component>();

    public void draw(){
        for (Component component:components) {
            component.draw();
        }
    }

    public void update(){
        for(Component component:components){
            component.update();
        }
    }

    public void handleInputs(){
        //create a queue where we'll save inputs and process them
    }

}
