package part2.part2_1;

/**
 * Задание 2.1 — Расчёт бонусов (точка входа)
 *
 * Как запустить: нажмите ▶ рядом с методом main.
 *
 * Ожидаемый вывод:
 *
 * === Расчёт бонусов ===
 * Ольга | Оклад: 120000.0 | Бонус: 43000.0 | Итого: 163000.0
 * Андрей | Оклад: 95000.0 | Бонус: 11400.0 | Итого: 106400.0
 * Мария | Оклад: 100000.0 | Бонус: 12000.0 | Итого: 112000.0
 * Стажёр Петя | Оклад: 30000.0 | Бонус: 10000.0 | Итого: 40000.0
 *
 * Общий бюджет: 421400 руб.
 */
public class EmployeeBonus {
    public static void main(String[] args) {
        Employee[] team = {
            new Manager("Ольга", 120000, 5),
            new Developer("Андрей", 95000, "Java"),
            new Developer("Мария", 100000, "Python"),
            new Intern("Стажёр Петя", 30000)
        };

        System.out.println("=== Расчёт бонусов ===");
        double totalBudget = 0;
        for (Employee e : team) {
            System.out.println(e);
            totalBudget += e.totalCompensation();
        }
        System.out.printf("\nОбщий бюджет: %.0f руб.%n", totalBudget);
    }
}
