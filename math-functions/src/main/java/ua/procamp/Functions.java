package ua.procamp;

public class Functions {
    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        intFunctionMap.addFunction("abs", (el) -> Math.abs(el));
        intFunctionMap.addFunction("sgn", (el) -> el < 0 ? -1 : el > 0 ? 1 : 0);
        intFunctionMap.addFunction("increment", (el) -> el + 1);
        intFunctionMap.addFunction("decrement", (el) -> el - 1);
        intFunctionMap.addFunction("square", (el) -> el * el);

        return intFunctionMap;
    }
}
