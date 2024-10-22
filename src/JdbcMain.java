import dao.EmpDAO;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);
        EmpDAO dao = new EmpDAO();

        while(true) {
            System.out.println("======== EMP TABLE ========");
            System.out.println("메뉴를 선택 하세요: ");
            System.out.print("[1]SELECT [2]INSERT [3]UPDATE [4]DELETE [5]EXIT: ");
            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    List<EmpVO> list = dao.empSelect();
                    dao.empSelectResult(list);
                    break;
                case 2:
                    empInput();

            }
        }
    }

    public static EmpVO empInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("사원 정보를 입력하세요");
        System.out.println("---------------------");
        System.out.print("사원번호(4자리): ");
        int empNo = sc.nextInt();
        System.out.print("사원이름: ");
        String name = sc.next();
        System.out.print("사원 직책: ");
        String job = sc.next();
        System.out.print("상관 사원번호: ");
        int mgr = sc.nextInt();
        System.out.print("입사일: ");
        // 2024-05-24
        String date = sc.next();    // 문자열로 입력 받아도 날짜 형식에 맞으면 입력 가능
        System.out.print("급여: ");
        BigDecimal sal = sc.nextBigDecimal();
        System.out.print("성과급: ");
        BigDecimal comm = sc.nextBigDecimal();
        System.out.print("부서번호: ");
        int deptNo = sc.nextInt();
        return new EmpVO(empNo, name, job, mgr, Date.valueOf(date), sal, comm, deptNo);
    }
}
