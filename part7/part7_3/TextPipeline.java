package part7.part7_3;

import java.util.*;
import java.util.function.Function;

/**
 * Задание 7.3 — Композиция функций и локальный класс
 *
 * Тема: Function<T, R>, композиция через andThen(), локальные классы.
 *
 * Ключевая теория:
 *   - Function<T, R> — функциональный интерфейс: принимает T, возвращает R.
 *   - f.andThen(g) = сначала f, потом g (f → g).
 *   - f.compose(g) = сначала g, потом f (g → f).
 *   - Локальный класс — класс, объявленный внутри метода. Виден только в этом методе.
 *
 * Как запустить: нажмите ▶ рядом с main.
 */
public class TextPipeline {

    public static void main(String[] args) {

        // === Часть A: Композиция функций ===
        // ▼ ВАШ КОД ЗДЕСЬ (Часть A) ▼
        // 1. Создаем атомарные функции
        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> removeExtraSpaces = s -> s.replaceAll("\\s+", " ");
        Function<String, String> capitalize = s -> s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);

        // 2. Склеиваем их в одну цепочку (Pipeline)
        Function<String, String> normalize = trim
                .andThen(lower)
                .andThen(removeExtraSpaces)
                .andThen(capitalize);

        // 3. Тестируем на строках
        String[] testStrings = {
                "  пРИВЕТ    МИР  ",
                "   jAVA   пРОГРАММИРОВАНИЕ   ",
                "ТЕСТ"
        };

        System.out.println("=== Результат нормализации ===");
        for (String s : testStrings) {
            System.out.println("\"" + s + "\" → \"" + normalize.apply(s) + "\"");
        }
        // TODO: создайте 4 функции Function<String, String>:
        //   Function<String, String> trim = String::trim;
        //   Function<String, String> lower = String::toLowerCase;
        //   Function<String, String> removeExtraSpaces = s -> s.replaceAll("\\s+", " ");
        //   Function<String, String> capitalize = s -> s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);

        // TODO: скомпонуйте в одну функцию:
        //   var normalize = trim.andThen(lower).andThen(removeExtraSpaces).andThen(capitalize);

        // TODO: примените к нескольким строкам:
        // ▲ КОНЕЦ ВАШЕГО КОДА (Часть A) ▲

        // === Часть B: Локальный класс ===
        // ▼ ВАШ КОД ЗДЕСЬ (Часть B) ▼
        class WordCounter {
            private final String text;

            WordCounter(String text) {
                this.text = text;
            }

            Map<String, Integer> count() {
                Map<String, Integer> map = new HashMap<>();
                // Разбиваем текст на слова по пробелу
                String[] words = text.split(" ");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        // merge увеличивает счетчик или ставит 1, если слова еще нет
                        map.merge(word, 1, Integer::sum);
                    }
                }
                return map;
            }

            String mostFrequent() {
                return count().entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("Нет данных");
            }
        }

        // Использование WordCounter
        String rawInput = "  java java PYTHON  java   python stream  ";
        String cleanInput = normalize.apply(rawInput);

        WordCounter wc = new WordCounter(cleanInput);

        System.out.println("\n=== Анализ текста ===");
        System.out.println("Нормализованная строка: " + cleanInput);
        System.out.println("Частота слов: " + wc.count());
        System.out.println("Самое частое слово: " + wc.mostFrequent());
        // TODO: объявите локальный класс WordCounter прямо здесь, внутри main:
        // TODO: используйте WordCounter для анализа нормализованной строки:
        // ▲ КОНЕЦ ВАШЕГО КОДА (Часть B) ▲
    }
}
