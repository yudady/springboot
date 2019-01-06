package tk.tommy.service;

import org.springframework.stereotype.Service;

@Service
public class Dog implements Animal {

    @Override
    public boolean accept(String name) {
        return name.equals("Dog");
    }
    @Override
    public void action() {
        System.out.println("Dog");
    }
}
