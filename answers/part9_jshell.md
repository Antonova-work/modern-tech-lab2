# Часть 9 — Эксперименты в jshell

## Как запустить jshell

Откройте терминал IntelliJ (View → Tool Windows → Terminal) и введите:
```
jshell
```
Для выхода: `/exit`

---

## Задание 9.1: Sealed-классы

### Команды (скопируйте и вставьте в jshell)

```
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}
Shape s = new Circle(5)
s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
```

### Фактический вывод:

```
|  modified interface Shape
|  modified record Circle
|  modified record Square
s ==> Circle[r=5.0]
$8 ==> "Круг r=5.0"
```

### Вопрос: Что произойдёт при попытке создать `record Triangle(double a) implements Shape {}`?

**Ваш ответ: Произойдет ошибка компиляции, так как интерфейс Shape является "запечатанным" (sealed) и явно разрешает наследование только для классов Circle и Square. Любая попытка реализовать этот интерфейс другим классом без добавления его в список permits будет заблокирована компилятором.**

---

## Задание 9.2: Цепочка лямбд

### Команды

```
import java.util.function.*
Function<String, String> trim = String::trim
Function<String, String> upper = String::toUpperCase
Function<String, String> exclaim = s -> s + "!"
var pipeline1 = trim.andThen(upper).andThen(exclaim)
var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline1.apply("  hello world  ")
pipeline2.apply("  hello world  ")
```

### Фактический вывод:

```
trim ==> $Lambda/0x00007ae784013208@59494225
upper ==> $Lambda/0x00007ae784013650@cb644e
exclaim ==> $Lambda/0x00007ae784013a98@4566e5bd
pipeline1 ==> java.util.function.Function$$Lambda/0x00007ae78405e5f0@69ea3742
pipeline2 ==> java.util.function.Function$$Lambda/0x00007ae78405e838@73846619
$16 ==> "HELLO WORLD!"
$17 ==> "HELLO WORLD!"
```

### Вопрос: Дают ли `andThen()` и `compose()` одинаковый результат? В каком случае результаты будут различаться?

**Ваш ответ: В данном случае результат одинаковый, но логика разная. f.andThen(g) сначала выполняет f, а затем g. f.compose(g) сначала выполняет g, а затем f. Результаты будут различаться, если порядок функций важен для итогового значения (например, сначала прибавить 1, а потом умножить на 2 — это не то же самое, что сначала умножить на 2, а потом прибавить 1).**



---

## Задание 9.3: Сравнение EnumSet и HashSet

### Команды

```
enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
enumSet.contains(Color.RED)
hashSet.contains(Color.RED)
enumSet.getClass().getSimpleName()
hashSet.getClass().getSimpleName()
```

### Фактический вывод:

```
|  created enum Color
enumSet ==> [RED, GREEN, BLUE]
hashSet ==> [GREEN, BLUE, RED]
$5 ==> true
$6 ==> true
$7 ==> "RegularEnumSet"
$8 ==> "HashSet"
```

### Вопрос: Почему внутренний класс EnumSet называется `RegularEnumSet`? Что произойдёт, если enum будет иметь больше 64 констант?

**Ваш ответ: RegularEnumSet используется для перечислений, размер которых не превышает 64 элементов, так как он хранит данные в одной переменной типа long (битовая маска). Если констант станет больше 64, Java автоматически переключится на реализацию JumboEnumSet, которая хранит битовую маску в массиве long[]**

