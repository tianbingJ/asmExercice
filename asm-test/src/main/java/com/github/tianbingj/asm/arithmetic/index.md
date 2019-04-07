## 说明

实现一个类ExerciseChild，继承Exercise，并且实现add方法

```$xslt
public class ExerciseChild extends Exercise {

    public int add(int number) {
        this.intValue += number;
        return this.intValue;
    }

    public double add(double number) {
        this.doubleValue += number;
        return this.doubleValue;
    }
}
```