import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("./input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            int[] memory = new int[32];

            // 1. 32바이트의 메모리를 먼저 적재합니다.
            memory[0] = Integer.parseInt(line, 2);
            for (int i = 1; i < 32; i++) {
                String nextLine = br.readLine();
                if (nextLine == null)
                    break;
                memory[i] = Integer.parseInt(nextLine, 2);
            }

            // 2. 가상 머신 상태 초기화
            int acc = 0; // 8비트 누산기
            int pc = 0; // 5비트 프로그램 카운터 (0~31)
            boolean isFinished = false;

            // 3. 시뮬레이션 시작 (Fetch -> Increment PC -> Execute)
            while (!isFinished) {
                // 현재 명령어 인출 (Fetch)
                int currentCommand = memory[pc];

                // PC 증가 (5비트 범위 내에서 순환)
                pc = (pc + 1) % 32;

                // 상위 3비트는 연산자(Opcode), 하위 5비트는 피연산자(Operand)
                int opcode = currentCommand >> 5;
                int operand = currentCommand & 31;

                switch (opcode) {
                    case 0: // 000: STA x (메모리 주소 x에 acc 저장)
                        memory[operand] = acc;
                        break;
                    case 1: // 001: LDA x (메모리 주소 x의 값을 acc로 로드)
                        acc = memory[operand];
                        break;
                    case 2: // 010: BEQ x (acc가 0이면 pc를 x로 변경)
                        if (acc == 0) {
                            pc = operand;
                        }
                        break;
                    case 3: // 011: NOP (아무것도 하지 않음)
                        break;
                    case 4: // 100: DEC (acc 값 1 감소, 8비트 언더플로우 처리)
                        acc = (acc - 1 + 256) % 256;
                        break;
                    case 5: // 101: INC (acc 값 1 증가, 8비트 오버플로우 처리)
                        acc = (acc + 1) % 256;
                        break;
                    case 6: // 110: JMP x (pc를 x로 변경)
                        pc = operand;
                        break;
                    case 7: // 111: HLT (프로그램 종료)
                        isFinished = true;
                        break;
                }
            }

            // 4. 결과 출력 (8자리 2진수 형태)
            System.out.println(toBinary8Bit(acc));
        }
    }

    // 8비트 2진수 문자열로 변환하는 메서드
    private static String toBinary8Bit(int value) {
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            sb.append((value >> i) & 1);
        }
        return sb.toString();
    }
}
