## 给类添加字段

```
  统计一个类花费的总时间
  给类添加一个静态的成员long timer:
  每个方法调用前执行timer -= System.currentTimeMillis();
  每个方法调用后执行timer += System.currentTimeMillis();
```

