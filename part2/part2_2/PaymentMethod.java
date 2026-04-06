package part2.part2_2;

/**
 * Задание 2.2 — Sealed-интерфейс системы оплаты
 *
 * Тема: sealed-интерфейсы (Java 17+) и паттерн-матчинг (Java 21+).
 *
 * Ключевая теория:
 *   - sealed interface ограничивает набор допустимых реализаций
 *     через permits.
 *   - Компилятор знает ВСЕ возможные реализации — это позволяет
 *     использовать исчерпывающий switch без default.
 *   - Каждый допустимый тип должен быть final, sealed
 *     или non-sealed. Record автоматически final.
 */
public sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {

    /**
     * Обрабатывает платёж и возвращает описание операции.
     *
     * @param amount сумма платежа
     * @return строка-описание выполненного платежа
     */
    String process(double amount);

    /**
     * Вычисляет комиссию за платёж.
     *
     * @param amount сумма платежа
     * @return размер комиссии
     */
    double fee(double amount);
}