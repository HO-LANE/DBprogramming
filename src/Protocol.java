import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Protocol extends Thread {
    private Socket cliSocket;

    public Protocol(Socket cliSocket) {
        this.cliSocket = cliSocket;
    }
    private static String getLineWithoutBlank(Scanner input){
        String tmp = input.nextLine();
        while( tmp.compareTo("") == 0){
            tmp = input.nextLine();
            System.out.println(tmp);
        }
        return tmp;
    }
    public void go(BufferedWriter bw, BufferedReader br, int status, String loginId) throws IOException {
        Scanner input = new Scanner(System.in);

        byte type= -1, firstType = -1, secondType= -1, thirdType= -1;
        byte code= -1;
        int length = 0;
        char[] data;

        char typeNcode;
        char responseTypeAndCode;
        char[] dataArr;
        String tmp;

        boolean login = true;

        // 수신 파트 ----------------------------------------------------------------------


        for (; login;) {

            // 고객 파트 ------------------------------------------------------------

            // 점주 파트 ------------------------------------------------------------

            if (firstType == 2)
            { // 주문 완료에 대한 대답
                if (secondType == 2){
                    if (thirdType == 2){
                        if(code == 1){
                            System.out.println("Complete Order Success!");
                        }
                        else if(code == 2)
                        {
                            System.out.println("Complete Order Reject");
                        }
                    }
                }
            }
            if (firstType == 2){ // 메뉴 수정 결과
                if (secondType == 2){
                    if (thirdType == 1){
                        if(code == 1){
                            System.out.println("Update Menu Success");
                        }
                        else if(code == 2)
                        {
                            System.out.println("Update Menu fail");
                        }
                    }
                }
            }

            // 관리자 파트 ---------------------------------------------------------------------

            // 주문 승인/거절
            if (firstType == 2) {
                if (secondType == 2) {
                    if (thirdType == 2) {
                        if (code == 1) {
                            System.out.println("Order accept Success!");
                        }
                    }
                }
            }
            if (firstType == 2) {
                if (secondType == 2) {
                    if (thirdType == 2) {
                        if (code == 2) {
                            System.out.println("Order accept Reject!");
                        }
                    }
                }
            }

            // 점주 승인/거절
            if (firstType == 2) {
                if (secondType == 2) {
                    if (thirdType == 4) {
                        if (code == 1) {
                            System.out.println("Owner accept Success!");
                        }
                    }
                }
            }
            if (firstType == 2) {
                if (secondType == 2) {
                    if (thirdType == 4) {
                        if (code == 2) {
                            System.out.println("Owner accept Reject!");
                        }
                    }
                }
            }

            // 가게 승인/거절
            if (firstType == 2) {
                if (secondType == 2) {
                    if (thirdType == 0) {
                        if (code == 1) {
                            System.out.println("Store accept Success!");
                        }
                    }
                }
            }
            if (firstType == 2) {
                if (secondType == 2) {
                    if (thirdType == 0) {
                        if (code == 2) {
                            System.out.println("Store accept Reject!");
                        }
                    }
                }
            }

            // Prompt 파트 ----------------------------------------------------------------------

            switch( status ){
                case 1:
                    System.out.println();
                    System.out.println("--------------");
                    System.out.println("고객 메뉴");
                    System.out.println("1. 개인정보 수정");
                    System.out.println("2. 전화번호 수정");
                    System.out.println("3. 음식점 조회"); // ok
                    System.out.println("4. 음식 주문"); // ok
                    System.out.println("5. 주문 취소");
                    System.out.println("6. 주문 내역 조회"); // ok
                    System.out.println("7. 리뷰와 별점 등록"); // ok
                    System.out.println("8. 로그 아웃"); // ok
                    System.out.print("번호 선택 : ");
                    tmp = getLineWithoutBlank(input);
                    switch(Integer.parseInt(tmp)){
                        case 1 :
                            System.out.println("수정할 아이디 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            // 0b/01/00/0100/00000110 || 1 0 4 6
                            responseTypeAndCode = 0b0100010000000110;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            System.out.println("수정할 비밀번호 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];

                            // 0b/01/00/0100/00000110 :: 1 0 4 7
                            responseTypeAndCode = 0b0100010000000111;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 2 :
                            System.out.println("수정할 아이디 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            // 0b/01/00/0100/00000110 :: 1 0 4 6
                            responseTypeAndCode = 0b0100010000000110;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            System.out.println("수정할 전화번호 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];

                            // 0b/01/00/0100/00000110  :: 1 0 4 8
                            responseTypeAndCode = 0b0100010000001000;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;

                        case 3 :
                            System.out.println("음식점 조회");
                            //data = getLineWithoutBlank(input).toCharArray();

                            // 00 / 01 / 0000 / 0000 0000
                            responseTypeAndCode = 0b0001000000000000;
                            length = (char) 0;
                            dataArr = new char[2];

                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            bw.write(dataArr);
                            bw.flush();

                            // 수신 파트 -------------------------------------------------------------------------

                            typeNcode = (char) br.read();
                            code = (byte) (typeNcode & 255);
                            type = (byte) (typeNcode >> 8);
                            thirdType = (byte) (type & 15);
                            type = (byte) (type >> 4);
                            secondType = (byte) (type & 3);
                            type = (byte) (type >> 2);
                            firstType = (byte) (type & 3);
                            length = br.read() + '0';

                            // 가게 조회
                            if (firstType == 2) {
                                if (secondType == 1) {
                                    if (thirdType == 0) {
                                        if (code == 1) {
                                            System.out.println("Store search Success!");

                                            // string 출력
                                            String result = "";
                                            for(int i = 0; i < length; i++) {
                                                result += (char)br.read();
                                            }
                                            System.out.println(result);

                                        } else if (code == 2) {
                                            System.out.println("Store search Fail");
                                        }
                                    }
                                }
                            }

                            break;
                        case 4:
                            System.out.println("[4] 메뉴 주문");

                            responseTypeAndCode = 0b0000001000000000;

                            dataArr = new char[2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = '0';
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100001000000001;
                            data = loginId.toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100001000000010;
                            System.out.print("메뉴 이름 입력: ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100001000000011;
                            System.out.print("메뉴 옵션 번호 입력: ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }

                            bw.write(dataArr);
                            bw.flush();
                            break;

                        case 5:
                            System.out.println("취소할 주문번호 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            //responseTypeAndCode = 0b/00/10/0010/00000101; 0  2  2  5
                            responseTypeAndCode = 0b0010001000000101;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 6:
                            System.out.println("주문 정보 조회");
                            System.out.print("사용자 ID 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();

                            // 00 / 01 / 0010 / 0000 0000
                            responseTypeAndCode = 0b0001001000000000;
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }

                            bw.write(dataArr);
                            bw.flush();

                            // 수신
                            typeNcode = (char) br.read();
                            code = (byte) (typeNcode & 255);
                            type = (byte) (typeNcode >> 8);
                            thirdType = (byte) (type & 15);
                            type = (byte) (type >> 4);
                            secondType = (byte) (type & 3);
                            type = (byte) (type >> 2);
                            firstType = (byte) (type & 3);
                            length = br.read() - '0';

                            // 주문 조회
                            if (firstType == 2) {
                                if (secondType == 1) {
                                    if (thirdType == 2) {
                                        if (code == 1) {
                                            System.out.println("Order search Success!");
                                            // string 출력
                                            String result = "";

                                            for(int i = 0; i < length; i++) {
                                                result += (char)br.read();
                                            }
                                            System.out.println(result);

                                        } else if (code == 2) {
                                            System.out.println("Order search Fail");
                                        }
                                    }
                                }
                            }

                            break;
                        case 7:
                            System.out.println("리뷰 및 별점 등록");

                            responseTypeAndCode = 0b0000001100000000;

                            dataArr = new char[2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = '0';

                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100001100000001;
                            System.out.print("주문 번호 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }

                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100001100000010;
                            System.out.print("별점 개수 등록 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }

                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100001100000011;
                            System.out.print("리뷰 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        default:
                            System.out.println("로그아웃");
                            login = false;
                            break;
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.println("--------------");
                    System.out.println("점주 메뉴");
                    System.out.println("1. 음식점 등록 신청"); // 현수
                    System.out.println("2. 메뉴 + 메뉴 옵션 신청"); // 현수
                    System.out.println("3. 음식점 영업 개시 시간 설정");
                    System.out.println("4. 음식점 영업 닫는 시간 설정");
                    System.out.println("5. 고객의 주문 접수 승인");
                    System.out.println("6. 고객의 주문 접수 거절");
                    System.out.println("7. 리뷰와 별점 조회"); // ok
                    System.out.println("8. 배달중인 주문 완료"); // ok
                    System.out.println("9. 메뉴 이름 수정"); // ok
                    System.out.println("10. 메뉴 가격 수정"); // ok
                    System.out.println("11. 메뉴 이름과 가격 수정"); // ok
                    System.out.println("12. 로그아웃"); // ok
                    System.out.print("번호 선택 : ");
                    tmp = getLineWithoutBlank(input);
                    switch(Integer.parseInt(tmp)){
                        case 1 :
                            responseTypeAndCode = 0b0100000000000001;
                            System.out.println("가게명 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            /////////////////////////

                            responseTypeAndCode = 0b0100000000000010;
                            System.out.println("가게 한줄 소개 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            /////////////////////////////
                            responseTypeAndCode = 0b0100000000000011;
                            System.out.println("위치 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///////////////////////////////////////

                            responseTypeAndCode = 0b0100000000000100;
                            System.out.println("가게 전화 번호 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///////////////////////////////////////

                            System.out.println("개시 시간 입력");
                            responseTypeAndCode = 0b0100000000000101;
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///////////////////////////////////////
                            System.out.println("닫는 시간 입력");
                            responseTypeAndCode = 0b0100000000000110;
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///////////////////////////////////////
                            responseTypeAndCode = 0b0100000000000111;
                            data = loginId.toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///////////////////////////////////////
                            break;
                        case 2 :
                            System.out.println("[2] 메뉴 등록");

                            responseTypeAndCode = 0b0000000100000000;
                            dataArr = new char[2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = '0';
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100000100000001;
                            System.out.print("메뉴명 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100000100000010;
                            System.out.print("카테고리 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100000100000011;
                            System.out.print("메뉴 가격 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100000100000100;
                            System.out.print("메뉴 판매량 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            // 가게 ID? 메뉴 ID?
                            responseTypeAndCode = 0b0100000100000101;
                            System.out.print("가게 ID 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            responseTypeAndCode = 0b0100000100000110;
                            System.out.print("옵션 번호 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///

                            break;

                            /*
                        firstType = 0;
                        secondType = 0;
                        thirdType = 5;
                        code = 0;
                        responseTypeAndCode = 0b0000010100000000;
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = '0';
                        dataArr[2] = '0';
                        bw.write(dataArr);
                        bw.flush();

                        firstType = 1;
                        secondType = 0;
                        thirdType = 5;
                        code = 1;
                        responseTypeAndCode = 0b0100010100000001;
                        System.out.print("Enter the option's name");
                        data = input.next().toCharArray();
                        length = (char)(data.length + '0');
                        dataArr = new char[data.length + 2];
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = length;
                        for(int i = 0; i<data.length; i++)
                        {
                            dataArr[i+2] = data[i];
                        }
                        bw.write(dataArr);
                        bw.flush();

                        firstType = 1;
                        secondType = 0;
                        thirdType = 5;
                        code = 2;
                        responseTypeAndCode = 0b0100010100000010;
                        System.out.print("Enter the option's price");
                        data = input.next().toCharArray();
                        length = (char)(data.length + '0');
                        dataArr = new char[data.length + 2];
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = length;
                        for(int i = 0; i<data.length; i++)
                        {
                            dataArr[i+2] = data[i];
                        }
                        bw.write(dataArr);
                        bw.flush();

                        firstType = 1;
                        secondType = 0;
                        thirdType = 5;
                        code = 3;
                        responseTypeAndCode = 0b0100010100000011;
                        System.out.print("Enter the option's num");
                        data = input.next().toCharArray();
                        length = (char)(data.length + '0');
                        dataArr = new char[data.length + 2];
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = length;
                        for(int i = 0; i<data.length; i++)
                        {
                            dataArr[i+2] = data[i];
                        }
                        bw.write(dataArr);
                        bw.flush();
                        break;
                             */
                        case 3:
                            System.out.println("가게 이름 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            //responseTypeAndCode = 0b/00/01/0000/0010100 0 1 0 10
                            responseTypeAndCode = 0b0001000000001010;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            System.out.println("개시 시간 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            //responseTypeAndCode = 0b/00/01/00/000010100 0 1 0 11
                            responseTypeAndCode = 0b0001000000001011;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 4 :
                            System.out.println("가게 이름 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            //responseTypeAndCode = 0b/00/01/00/000010100 0 1 0 10
                            responseTypeAndCode = 0b0001000000001010;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            System.out.println("닫는 시간 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            //responseTypeAndCode = 0b/00/01/00/000010100 0 1 0 12
                            responseTypeAndCode = 0b0001000000001100;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;

                        case 5 :
                            System.out.println("승인할 주문 번호 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            //responseTypeAndCode = 0b/00/10/0010/00000011; 0  2  2  3
                            responseTypeAndCode = 0b0010001000000011;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            break;
                        case 6:
                            System.out.println("거절할 주문 번호 입력");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            //responseTypeAndCode = 0b/00/10/0010/00000100; 0  2  2  4
                            responseTypeAndCode = 0b0010001000000100;
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;

                        case 7:
                            System.out.println("[7] 별점 및 후기 조회");
                            System.out.print("몇 페이지 : ");
                            data = getLineWithoutBlank(input).toCharArray();

                            // 00 / 01 / 0011 / 0000 0000
                            responseTypeAndCode = 0b0001001100000000;
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            ///////////////////////////////////////

                            System.out.print("몇 줄 표현 : ");
                            data = getLineWithoutBlank(input).toCharArray();

                            // 00 / 01 / 0011 / 0000 0001
                            responseTypeAndCode = 0b0001001100000001;
                            length = (char)(data.length + '0');

                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            // 수신
                            typeNcode = (char) br.read();
                            code = (byte) (typeNcode & 255);
                            type = (byte) (typeNcode >> 8);
                            thirdType = (byte) (type & 15);
                            type = (byte) (type >> 4);
                            secondType = (byte) (type & 3);
                            type = (byte) (type >> 2);
                            firstType = (byte) (type & 3);
                            length = br.read() + '0';

                            // 리뷰 정보 조회
                            if (firstType == 2) {
                                if (secondType == 1) {
                                    if (thirdType == 3) {
                                        if (code == 1) {
                                            System.out.println("Review search Success!");
                                            // string 출력
                                            String result = "";
                                            for(int i = 0; i < length; i++) {
                                                result += (char)br.read();
                                            }
                                            System.out.println(result);

                                        } else if (code == 2) {
                                            System.out.println("Review search Fail");
                                        }
                                    }
                                }
                            }

                            break;
                        case 8:
                            System.out.println("[8] 배달중인 주문 완료");
                            // firstType = 0; secondType = 2; thirdType =2; code = 1;
                            responseTypeAndCode = 0b0010001000000001;

                            System.out.print("주문 번호 입력 : ");

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            break;
                        case 9:
                            System.out.println("[9] 메뉴 이름 수정");
                            // firstType = 0; secondType = 2; thirdType =1; code = 1;
                            responseTypeAndCode = 0b0010000100000001;
                            System.out.print("수정할 메뉴 이름 입력 : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            System.out.println("Enter new MenuName");
                            //responseTypeAndCode = 0b/01/10/0001/00000010
                            responseTypeAndCode = 0b0110000100000010;

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 10: //TODO
                            System.out.println("[10] 메뉴 가격 수정");
                            // firstType = 0; secondType = 2; thirdType = 1; code = 3;
                            responseTypeAndCode = 0b0010000100000011;
                            System.out.print("수정할 메뉴 이름 : ");
                            System.out.print("enter Order MenuPrice : ");
                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            System.out.println("Enter new MenuPrice");
                            //responseTypeAndCode = 0b/01/10/0001/00000100
                            responseTypeAndCode = 0b0110000100000100;

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 11:
                            System.out.println("[11] 메뉴명 및 가격 수정");

                            firstType = 0; secondType = 2; thirdType = 1; code = 5;
                            responseTypeAndCode = 0b0010000100000101;
                            System.out.print("수정할 메뉴명과 가격 : ");
                            System.out.print("enter Order MenuName : ");

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            firstType = 1; secondType = 2; thirdType =1; code = 6;
                            System.out.println("Enter new MenuName");
                            //responseTypeAndCode = 0b/01/10/0001/00000110
                            responseTypeAndCode = 0b0110000100000110;

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();

                            firstType = 1; secondType = 2; thirdType =1;
                            code = 7;
                            System.out.println("Enter new MenuPrice");
                            //responseTypeAndCode = 0b/01/10/0001/00000111
                            responseTypeAndCode = 0b0110000100000111;

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        default:
                            System.out.println("로그아웃");
                            login = false;
                            break;

                    }
                    break;

                case 3:
                    System.out.println();
                    System.out.println("--------------");
                    System.out.println("관리자 메뉴");
                    System.out.println("1. 메뉴 승인"); // ok
                    System.out.println("2. 메뉴 거절"); // ok
                    System.out.println("3. 점주 승인"); // ok
                    System.out.println("4. 점주 거절"); // ok
                    System.out.println("5. 가게 승인"); // ok
                    System.out.println("6. 가게 거절"); // ok
                    System.out.println("7. 로그아웃"); // ok
                    System.out.print("메뉴 번호 입력 : ");
                    tmp = input.nextLine();
                    while(!("0".compareTo(tmp) <= 0 && "9".compareTo(tmp) >= 0)){
                        tmp = input.nextLine();
                        System.out.println(tmp);
                    }
                    switch(Integer.parseInt(tmp)){
                        case 1:
                            System.out.println("[1] 메뉴 등록 승인");

                            firstType = 0; secondType = 2; thirdType = 1; code = 10;
                            responseTypeAndCode = 0b0010000100001010;
                            System.out.print("등록할 메뉴명  : ");

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                        case 2:
                            System.out.println("[2] 메뉴 등록 거절");

                            firstType = 0; secondType = 2; thirdType = 1; code = 11;
                            responseTypeAndCode = 0b0010000100001011;
                            System.out.print("거절할 메뉴명  : ");

                            data = getLineWithoutBlank(input).toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char) length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 3:
                            System.out.print("점주 번호 입력");
                            String userId = getLineWithoutBlank(input);
                            responseTypeAndCode = 0b0001010000000000;
                            data = userId.toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 4 :
                            System.out.print("점주 번호 입력");
                            userId = getLineWithoutBlank(input);
                            responseTypeAndCode = 0b0001010000000001;
                            data = userId.toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 5:
                            System.out.print("가게명 입력");
                            String storeNum = getLineWithoutBlank(input);
                            responseTypeAndCode = 0b0001000000000000;
                            data = storeNum.toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        case 6:
                            System.out.print("가게명 입력");
                            storeNum = getLineWithoutBlank(input);
                            responseTypeAndCode = 0b0001000000000001;
                            data = storeNum.toCharArray();
                            length = (char)(data.length + '0');
                            dataArr = new char[data.length + 2];
                            dataArr[0] = responseTypeAndCode;
                            dataArr[1] = (char)length;
                            for(int i = 0; i<data.length; i++)
                            {
                                dataArr[i+2] = data[i];
                            }
                            bw.write(dataArr);
                            bw.flush();
                            break;
                        default:
                            System.out.println("로그아웃");
                            login = false;
                            break;
                    }
                    break;

                default :
                    login = false;
                    System.out.println("다시 로그인");

            }
        }
    }
    public void run() {
        OutputStream os; BufferedReader keyInput; BufferedWriter bw;
        InputStream is; BufferedReader br;

        try {
            os = cliSocket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));

            is = cliSocket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            Scanner input = new Scanner(System.in);

            byte type, firstType, secondType, thirdType;
            byte code;
            int length = 0;
            char[] data;

            char typeNcode;
            char responseTypeAndCode;
            char[] dataArr;
            String tmp;

            boolean login = false;
            String loginId = null;

            // 시작 화면 ----------------------------------------------------------------------

            c: for(;true;){
                System.out.println("1. 점주 회원가입");
                System.out.println("2. 고객 회원가입");
                System.out.println("3. 로그인");
                System.out.print("기능 선택: ");
                tmp = getLineWithoutBlank(input);
                char resigerStatus = '0';
                switch(Integer.parseInt(tmp)){
                    case 1 :
                        if (resigerStatus == '0')
                            resigerStatus = '5';
                    case 2 :
                        if (resigerStatus == '0')
                            resigerStatus = '1';
                        System.out.println("id");
                        data = getLineWithoutBlank(input).toCharArray();
                        length = (char) (data.length + '0');
                        dataArr = new char[data.length + 2];
                        responseTypeAndCode = 0b0100010000000001;
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = (char) (length);

                        for (int i = 0; i < data.length; i++) {
                            dataArr[i + 2] = data[i];
                        }

                        bw.write(dataArr);
                        bw.flush();

                        System.out.println("passwd");
                        data = getLineWithoutBlank(input).toCharArray();
                        length = (char) (data.length + '0');
                        dataArr = new char[data.length + 2];
                        responseTypeAndCode = 0b0100010000000010;
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = (char) (length);

                        for (int i = 0; i < data.length; i++) {
                            dataArr[i + 2] = data[i];
                        }

                        bw.write(dataArr);
                        bw.flush();

                        System.out.println("name");
                        data = getLineWithoutBlank(input).toCharArray();
                        length = (char) (data.length + '0');
                        dataArr = new char[data.length + 2];
                        responseTypeAndCode = 0b0100010000000011;
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = (char) (length);

                        for (int i = 0; i < data.length; i++) {
                            dataArr[i + 2] = data[i];
                        }

                        bw.write(dataArr);
                        bw.flush();

                        System.out.println("phone number");
                        data = getLineWithoutBlank(input).toCharArray();
                        length = (char) (data.length + '0');
                        dataArr = new char[data.length + 2];
                        responseTypeAndCode = 0b0100010000000100;
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = (char) (length);

                        for (int i = 0; i < data.length; i++) {
                            dataArr[i + 2] = data[i];
                        }

                        bw.write(dataArr);
                        bw.flush();

                        //유저 지위 정보 전송
                        data = (resigerStatus + "").toCharArray();
                        length = (char) (data.length + '0');
                        dataArr = new char[data.length + 2];
                        responseTypeAndCode = 0b0100010000000101;
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = (char) (length);

                        for (int i = 0; i < data.length; i++) {
                            dataArr[i + 2] = data[i];
                        }

                        bw.write(dataArr);
                        bw.flush();
                        break;
                    case 3 :
                        int status = 0;
                        System.out.println("Login");
                        // 유저의 로그인 요청
                        System.out.print("ID : ");
                        loginId = getLineWithoutBlank(input);
                        data = loginId.toCharArray();
                        length = (char) (data.length + '0');
                        dataArr = new char[data.length + 2];
                        responseTypeAndCode = 0b0101010000000000;
                        dataArr[0] = responseTypeAndCode;
                        dataArr[1] = (char) (length);

                        for (int i = 0; i < data.length; i++) {
                            dataArr[i + 2] = data[i];
                        }

                        bw.write(dataArr);
                        bw.flush();

                        typeNcode = (char) br.read();
                        code = (byte) (typeNcode & 255);
                        type = (byte) (typeNcode >> 8);
                        thirdType = (byte) (type & 15);
                        type = (byte) (type >> 4);
                        secondType = (byte) (type & 3);
                        type = (byte) (type >> 2);
                        firstType = (byte) (type & 3);
                        length = br.read();

                        // PWD 전달
                        if (firstType == 0) {
                            if (secondType == 1) {
                                if (thirdType == 4) {
                                    if (code == 1) {
                                        System.out.print("PWD : ");
                                        tmp = input.next();
                                        if (tmp == "")
                                            tmp = input.next();
                                        data = tmp.toCharArray();

                                        responseTypeAndCode = 0b0101010000000010;
                                        length = (char) (data.length + '0');

                                        dataArr = new char[data.length + 2];
                                        dataArr[0] = responseTypeAndCode;
                                        dataArr[1] = (char) (length);
                                        for (int i = 0; i < data.length; i++) {
                                            dataArr[i + 2] = data[i];
                                        }

                                        bw.write(dataArr);
                                        bw.flush();
                                    }
                                }
                            }
                        }

                        typeNcode = (char) br.read();
                        code = (byte) (typeNcode & 255);
                        type = (byte) (typeNcode >> 8);
                        thirdType = (byte) (type & 15);
                        type = (byte) (type >> 4);
                        secondType = (byte) (type & 3);
                        type = (byte) (type >> 2);
                        firstType = (byte) (type & 3);
                        length = br.read();

                        // 0 : 대기, 1 : 고객, 2 : 점주, 3 : 관리자
                        if (firstType == 2) {
                            if (secondType == 1) {
                                if (thirdType == 4) {
                                    if (code == 1) {
                                        System.out.println("고객으로 Login Success!");
                                        status = 1;
                                    } else if (code == 2) {
                                        System.out.println("점주로 Login Success!");
                                        status = 2;
                                    } else if (code == 3) {
                                        System.out.println("관리자로 Login Success!");
                                        status = 3;
                                    } else {
                                        System.out.println("Login Fail");
                                    }
                                }
                            }

                        }
                        go(bw, br, status, loginId);
                        break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}