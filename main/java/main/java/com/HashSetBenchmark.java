package main.java.com;

import com.Employee;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1)
public class HashSetBenchmark {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(MyState.class.getSimpleName())
             //   .forks(1)
                .build();

        new Runner(opt).run();
    }
    @State(Scope.Thread)
    public static class MyState {
        private Set <String> employeeSet1 = new HashSet<>();
        private List<String> employeeList1 = new ArrayList<>();
        private Set <String> employeeSet2 = new HashSet<>();
        private List<String> employeeList2 = new ArrayList<>();
        private Set<Employee> employeeSet3 = new HashSet<>();
        private Set<Employee> employeeSet4 = new HashSet<>();

        private long set1Size = 600000;
        private long list1Size = 500000;
        private long set2Size = 500000;
        private long list2Size = 600000;
        private long set3Size = 500000;
        private long set4Size = 600000;

        @Setup(Level.Trial)
        public void setUp() {

            for(int  i =0; i<set1Size;i++)
            {
                employeeSet1.add("slv;slsfnsfldsfnsdfvsfvsfvs"+i);
                employeeSet2.add("slv;slsfnsfldsfnsdfvsfvsfvs"+i);
            }
            for(int  i =0; i<set1Size;i++)
            {
                employeeList1.add("slv;slsfnsfldsfnsdfvsfvsfvs"+i);
                employeeList2.add("slv;slsfnsfldsfnsdfvsfvsfvs"+i);
            }

            given_SizeOfHashsetGreaterThanSizeOfCollection_whenRemoveAllFromHashSet_thenGoodPerformance(new MyState());
            given_SizeOfHashsetSmallerThanSizeOfCollection_whenRemoveAllFromHashSet_thenBadPerformance(new MyState());
        }
        @Benchmark
        @Warmup(timeUnit = TimeUnit.SECONDS)
        public boolean given_SizeOfHashsetGreaterThanSizeOfCollection_whenRemoveAllFromHashSet_thenGoodPerformance(MyState state) {
            return state.employeeSet1.removeAll(state.employeeSet2);
        }

        @Benchmark
        @Warmup( timeUnit = TimeUnit.SECONDS)
        public boolean given_SizeOfHashsetSmallerThanSizeOfCollection_whenRemoveAllFromHashSet_thenBadPerformance(MyState state) {
            return state.employeeList1.removeAll(state.employeeList1);
        }

        @Benchmark
        public boolean given_SizeOfHashsetSmallerThanSizeOfAnotherHashSet_whenRemoveAllFromHashSet_thenGoodPerformance(MyState state) {
            return state.employeeSet3.removeAll(state.employeeSet4);
        }

    }


}
