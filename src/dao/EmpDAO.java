package dao;

import common.Common;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// DAO(Database Access Object): 데이터베이스에 접근하여 데이터를 조회하거나 수정하는 데 사용
// DAO 는 VO 객체와 데이터베시으 간의 매핑을 담당
public class EmpDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = null;

    public EmpDAO() {
        sc = new Scanner(System.in);
    }
    // Select 기능(조회) 구현
    public List<EmpVO> empSelect() {
        List<EmpVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();  // 오라클 DB 연결
            stmt = conn.createStatement();  // statement 생성
            String query = "SELECT * FROM EMP";
            // executeQuery(): select 문과 같이 결과값이 여러개의 레코드로 반환되는 경우 사용
            // rs(ResultSet): 여러행의 결과값을 받아서 반복자 제공
            rs = stmt.executeQuery(query);
            // next(): 현재 행에서 한 행 앞으로 이동
            // previous(): 현재 행에서 한 행 뒤로 이동
            // first(): 첫 번째 행으로 이동
            // last(): 마지막 행으로 이동
            while(rs.next()) {
                int empNo = rs.getInt("EMPNO");
                String name = rs.getString("ENAME");
                String job = rs.getString("JOB");
                int mgr = rs.getInt("MGR");
                Date date = rs.getDate("HIREDATE");
                BigDecimal sal = rs.getBigDecimal("SAL");
                BigDecimal comm = rs.getBigDecimal("COMM");
                int deptNo = rs.getInt("DEPTNO");

                EmpVO vo = new EmpVO(empNo, name, job, mgr, date, sal, comm, deptNo);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            System.out.println("EMP 조회 실패");
        }
        return list;
    }
    // INSERT 구현
    public void empInsert(EmpVO vo) {
    }






    public void empSelectResult(List<EmpVO> list) {
        System.out.println("----------------------------------------------");
        System.out.println("                   사원정보");
        System.out.println("----------------------------------------------");
        for(EmpVO e : list) {
            System.out.print(e.getEmpNO() + " ");
            System.out.print(e.getName() + " ");
            System.out.print(e.getJob() + " ");
            System.out.print(e.getMgr() + " ");
            System.out.print(e.getDate() + " ");
            System.out.print(e.getSal() + " ");
            System.out.print(e.getComm() + " ");
            System.out.print(e.getDeptNO());
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }

}


