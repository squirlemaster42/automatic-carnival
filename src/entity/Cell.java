package entity;

public class Cell {

    private int id, x, y, age;

    public Cell(int id, int x, int y, int age){
        this.id = id;
        this.x = x;
        this.y = y;
        this.age = age;
    }

    public void update(int xMove, int yMove, boolean age){
        if(age){
            this.age++;
        }
        x += xMove;
        y += yMove;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", age=" + age +
                '}';
    }
}
