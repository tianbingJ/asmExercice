## 说明

实现一个类ExerciseChild，继承Exercise，并且实现whileInt方法

```$xslt
public class ExerciseChild extends Exercise {

    public void whileInt(int count) {
        this.intValue = 0;
        while (intValue < count) {
            intValue ++;
        }
    }
}
```