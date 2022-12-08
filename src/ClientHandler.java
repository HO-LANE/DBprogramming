import CONTROL.*;
import DAO.MenuDAO;

import java.io.*;
import java.net.Socket;

class ClientHandler extends Thread{
    private Socket commSocket;
    private ClientsList cliList;
    private String nickname;

    public ClientHandler(Socket commSocket, ClientsList cliList){
        this.commSocket = commSocket;
        this.cliList = cliList;
    }

    public void run(){
        InputStream is;
        OutputStream os;
        BufferedReader br;
        BufferedWriter bw;
        String readBuf;

        try{
            is = commSocket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));

            os = commSocket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));

            byte type, firstType, secondType, thirdType;
            byte code;
            int length;
            String data;

            char typeNcode;
            char responseTypeAndCode = 0;
            char[] dataArr;
            String[] receiveDataArr = new String[10];

            // 사용자 정보
            String id = null;
            String passwd = null;
            String name = null;
            String phoneNum = null;
            String statusStr = null;
            // 가게 정보
            String storeName = null;
            String introduce = null;
            String location = null;
            String callNumber = null;
            String open = null;
            String close = null;

            for(int j = 0; j < 10 ; j++) {
                typeNcode = (char) br.read();
                length = br.read() - 48;
                data = "";
                for(int i = 0; i < length; i++) {
                    data += (char)br.read();
                }

                System.out.println("data = " + data);

                // Type & Code 분리
                code = (byte) (typeNcode & 255); type = (byte) (typeNcode >> 8);
                thirdType = (byte) (type & 15); type = (byte) (type >> 4);
                secondType = (byte) (type & 3); type = (byte) (type >> 2);
                firstType = (byte) (type & 3);
                System.out.println("Type & Code : " + firstType + " " + secondType + " " + thirdType + " " + code);

                // 로그인
                if (firstType == 1) { // 요청
                    if (secondType == 1) { // 조회
                        if (thirdType == 4) { // 유저
                            if (code == 0) { // 로그인 요청
                                System.out.println("Login Start");

                                System.out.println("ID receive");
                                receiveDataArr[0] = data;

                                responseTypeAndCode = 0b0001010000000001;

                                dataArr = new char[2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = '0';

                                bw.write(dataArr);
                                bw.flush();
                            }
                        }
                    }
                }

                if (firstType == 1) { // 요청
                    if (secondType == 1) { // 조회
                        if (thirdType == 4) { // 유저
                            if (code == 2) { // pwd 요청
                                System.out.println("PWD receive");
                                receiveDataArr[1] = data;

                                Login login = new Login();
                                int status = login.execute(receiveDataArr[0], receiveDataArr[1]);
                                System.out.println(status);
                                switch(status){
                                    case 0 :
                                        responseTypeAndCode = 0b1001010000000000;
                                        break;
                                    case 1 :
                                        responseTypeAndCode = 0b1001010000000001;
                                        break;
                                    case 2 :
                                        responseTypeAndCode = 0b1001010000000010;
                                        break;
                                    case 3 :
                                        responseTypeAndCode = 0b1001010000000011;
                                        break;
                                    default :
                                        responseTypeAndCode = 0b1001010000000100;
                                        break;
                                }

                                dataArr = new char[2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = '0';

                                bw.write(dataArr);
                                bw.flush();
                            }
                        }
                    }
                }
                if (firstType == 0) {
                    if (secondType == 1) {
                        if (thirdType == 0) {
                            if (code == 10) {
                                receiveDataArr[0] = data;
                            }
                        }
                    }
                }

                if (firstType == 0) {
                    if (secondType == 1) {
                        if (thirdType == 0) {
                            if (code == 11) { // 개시 시간 설정
//                                UpdateRunningTime updateRunningTime = new UpdateRunningTime();
//                                updateRunningTime.changeOpeningTime(receiveDataArr[0], data);
                            }
                        }
                    }
                }
                if (firstType == 0) {
                    if (secondType == 1) {
                        if (thirdType == 0) {
                            if (code == 12) { // 종료 시간 설정
//                                UpdateRunningTime updateRunningTime = new UpdateRunningTime();
//                                updateRunningTime.changeClosingTime(receiveDataArr[0], data);

                            }
                        }
                    }
                }

                if (firstType == 0) {
                    if (secondType == 2) {
                        if (thirdType == 2) {
                            if (code == 3) { //주문 접수
//                                AcceptOrder acceptorder = new AcceptOrder();
//                                acceptorder.execute(Integer.parseInt(data));
                            }
                        }
                    }
                }
                if (firstType == 0) {
                    if (secondType == 2) {
                        if (thirdType == 2) {
                            if (code == 4) {//주문 거절
//                                RejectOrder rejectorder = new RejectOrder();
//                                rejectorder.execute(Integer.parseInt(data));
                            }
                        }
                    }
                }

                // 회원가입 파트
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 4) {
                            if (code == 1) {
                                id = data;
                            }
                        }
                    }
                }

                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 4) {
                            if (code == 2) {
                                passwd = data;
                            }
                        }
                    }
                }

                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 4) {
                            if (code == 3) {
                                name = data;
                            }
                        }
                    }
                }

                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 4) {
                            if (code == 4) {
                                phoneNum = data;
                            }
                        }
                    }
                }

                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 4) {
                            if (code == 5) {
                                statusStr = data;
                                System.out.println(id+ passwd+ name+ phoneNum+ statusStr);
                                RegisterUser registerUser = new RegisterUser();
//                                registerUser.execute(id, passwd, name, phoneNum, statusStr);
                                id = null;
                                passwd = null;
                                name = null;
                                phoneNum = null;
                                statusStr = null;
                            }
                        }
                    }
                }

                // 가게 신청
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 0) {
                            if (code == 1) {
                                storeName = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 0) {
                            if (code == 2) {
                                introduce = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 0) {
                            if (code == 3) {
                                location = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 0) {
                            if (code == 4) {
                                callNumber = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 0) {
                            if (code == 5) {
                                open = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 0) {
                            if (code == 6) {
                                close = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 0) {
                            if (code == 7) {
                                id = data;
                                RegisterStore registerStore = new RegisterStore();
//                                registerStore.execute(storeName, introduce, location, callNumber, open, close, id);
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 1) {
                            if (code == 1) {
                                receiveDataArr[0] = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 1) {
                            if (code == 2) {
                                receiveDataArr[1] = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 1) {
                            if (code == 3) {
                                receiveDataArr[2] = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 1) {
                            if (code == 4) {
                                receiveDataArr[3] = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 1) {
                            if (code == 5) {
                                receiveDataArr[4] = data;
                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 1) {
                            if (code == 6) {
                                receiveDataArr[5] = data;
                                RegisterMenu registerMenu = new RegisterMenu();
//                                registerMenu.execute(receiveDataArr[0], receiveDataArr[1], Integer.parseInt(receiveDataArr[2]), Integer.parseInt(receiveDataArr[3]), receiveDataArr[4], receiveDataArr[5]);

                            }
                        }
                    }
                }
                if (firstType == 1) {
                    if (secondType == 0) {
                        if (thirdType == 1) {
                            if (code == 7) {
                                receiveDataArr[6] = data;
                                firstType = 2;
                                secondType = 0;
                                thirdType = 1;
                                code = 1;
                                //responseTypeAndCode = 0b/00/00/0001/00000110;
                                responseTypeAndCode = 0b1000000100000001;

                                length = 0;
                                dataArr = new char[2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char)length;
                                bw.write(dataArr);
                                bw.flush();

                            }
                        }
                    }
                }
//

                // 가게 조회 파트
                if (firstType == 0) { // 요청
                    if (secondType == 1) { // 조회
                        if (thirdType == 0) { // 가게
                            if(code == 0) { // 가게 조회 요청
                                System.out.println("Store Search");

                                SearchStore searchStore = new SearchStore();
                                String list = searchStore.printAcceptedStore();

                                // TYPE & CODE : 10 / 01 / 0000 / 0000 0001
                                responseTypeAndCode = 0b1001000000000001;
                                length = list.length();
                                char[] listArr = list.toCharArray();

                                dataArr = new char[list.length() + 2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char) (length - '0');
                                for(int i = 0; i < list.length(); i++) {
                                    dataArr[i + 2] = listArr[i];
                                }

                                bw.write(dataArr);
                                bw.flush();
                            }
                        }
                    }
                }

                // 메뉴 조회
                if (firstType == 0) { // 요청
                    if (secondType == 1) { // 조회
                        if (thirdType == 1) { // 메뉴
                            if (code == 0) { // 메뉴 조회 요청
                                System.out.println("Menu Search");

                                SearchMenu searchMenu = new SearchMenu();
                                String list = searchMenu.printAllMenu(data);

                                // TYPE & CODE : 10 / 01 / 0001 / 0000 0001 : 2 1 1 1
                                responseTypeAndCode = 0b1001000100000001;
                                length = list.length();
                                char[] listArr = list.toCharArray();

                                dataArr = new char[list.length() + 2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char) (length - '0');
                                for(int i = 0; i < list.length(); i++) {
                                    dataArr[i + 2] = listArr[i];
                                }

                                bw.write(dataArr);
                                bw.flush();
                            }
                        }
                    }
                }

                // 주문 조회
                if (firstType == 0){ // 요청
                    if (secondType == 1){ // 조회
                        if (thirdType == 2){ // 주문
                            if(code == 0){
                                System.out.println("Order Search");

                                SearchOrders searchOrders = new SearchOrders();
                                String list = searchOrders.printAllOrdersForClient(data);

                                // TYPE & CODE : 10 / 01 / 0010 / 0000 0001 : 2 1 2 1
                                responseTypeAndCode = 0b1001001000000001;
                                length = list.length();
                                char[] listArr = list.toCharArray();

                                dataArr = new char[list.length() + 2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char) (length - '0');
                                for(int i = 0; i < list.length(); i++) {
                                    dataArr[i + 2] = listArr[i];
                                }

                                bw.write(dataArr);
                                bw.flush();
                            }
                        }
                    }
                }

                // 리뷰 조회
                if (firstType == 0){
                    if (secondType == 1){
                        if (thirdType == 3){
                            if(code == 0){
                                System.out.println("Review Search");

                                receiveDataArr[0] = data;
                            }
                        }
                    }
                }
                if (firstType == 0){
                    if (secondType == 1){
                        if (thirdType == 3){
                            if(code == 1){
                                receiveDataArr[1] = data;

                                SearchReview searchReview = new SearchReview();
                                String list = searchReview.printAllReview(Integer.parseInt(receiveDataArr[0]), Integer.parseInt(receiveDataArr[1]));

                                // TYPE & CODE : 10 / 01 / 0011 / 0000 0001
                                responseTypeAndCode = 0b1001001100000001;
                                length = list.length();
                                char[] listArr = list.toCharArray();

                                dataArr = new char[list.length() + 2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char) (length - '0');
                                for(int i = 0; i < list.length(); i++) {
                                    dataArr[i + 2] = listArr[i];
                                }

                                bw.write(dataArr);
                                bw.flush();
                            }
                        }
                    }
                }


                //배달중인 주문 완료 요청
                if (firstType == 0) {
                    if (secondType == 2) { //변경이므로 10 즉 2
                        if (thirdType == 2) { //주문은 010 즉 2이다.
                            if (code == 1) {
                                System.out.println("Order Complete Request");

                                CompleteOrder completeOrder = new CompleteOrder();
                                completeOrder.execute(Integer.parseInt(data));

                                //type&code = 01 / 10 / 0010 / 0000 0001;
                                responseTypeAndCode = 0b0110001000000001;
                                length = 0;

                                dataArr = new char[2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char)length;

                                bw.write(dataArr);
                                bw.flush();
                            }
                        }
                    }
                }

                // 메뉴 변경중 메뉴이름만
                if (firstType == 0){
                    if (secondType == 2){ //변경이므로 2
                        if (thirdType == 1){ //메뉴는 001 즉 1이다.
                            if(code == 1){
                                receiveDataArr[0] = data; //oldMenuName
                            }
                        }
                    }
                }
                if (firstType == 1){
                    if (secondType == 2){ //변경이므로 2
                        if (thirdType == 1){ //메뉴는 001 즉 1이다.
                            if(code == 2){
                                receiveDataArr[1] = data; //newMenuName

                                UpdateMenu updateMenu = new UpdateMenu();
                                updateMenu.updateMenuName(receiveDataArr[0],receiveDataArr[1]);

                                //typeNcode = 0b/10/10/0001/00000001;
                                responseTypeAndCode = 0b1010000100000001;
                                length = 0;
                                dataArr = new char[2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char)length;
                                bw.write(dataArr);
                                bw.flush();


                            }
                        }
                    }
                }

                if (firstType == 0){
                    if (secondType == 2){ //변경이므로 2
                        if (thirdType == 1){ //메뉴는 001 즉 1이다.
                            if(code == 3){
                                receiveDataArr[0] = data;
                            }
                        }
                    }
                }
                if (firstType == 1){
                    if (secondType == 2){ //변경이므로 2
                        if (thirdType == 1){ //메뉴는 001 즉 1이다.
                            if(code == 4){
                                receiveDataArr[1] = data; //newPrice

                                UpdateMenu updateMenu = new UpdateMenu();
                                updateMenu.updateMenuPrice(receiveDataArr[0],Integer.parseInt(receiveDataArr[1]));

                                firstType = 2; secondType = 2; thirdType =1;
                                code = 00; //성공 메시지 0, 실패 1

                                //typeNcode = 0b/10/10/0001/00000001;
                                responseTypeAndCode = 0b1010000100000001;
                                length = 0;
                                dataArr = new char[2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char)length;
                                bw.write(dataArr);
                                bw.flush();

                            }
                        }
                    }
                }

                //메뉴 변경 중 이름과 가격
                if (firstType == 0){
                    if (secondType == 2){ //변경이므로 2
                        if (thirdType == 1){ //메뉴는 001 즉 1이다.
                            if(code == 5){
                                receiveDataArr = new String[3];
                                receiveDataArr[0] = data; //oldName
                            }
                        }
                    }
                }

                if (firstType == 1){
                    if (secondType == 2){ //변경이므로 2
                        if (thirdType == 1){ //메뉴는 001 즉 1이다.
                            if(code == 6){
                                receiveDataArr[1] = data; //newName
                                //새로운 가격요청

                            }
                        }
                    }
                }

                if (firstType == 1){
                    if (secondType == 2){
                        if (thirdType == 1){
                            if(code == 7){
                                receiveDataArr[2] = data; //newPrice

                                UpdateMenu updateMenu = new UpdateMenu();
                                updateMenu.updateMenuNameAndPrice(receiveDataArr[0],receiveDataArr[1],Integer.parseInt(receiveDataArr[2]));
                                responseTypeAndCode = 0b1010000100000001;
                                length = 0;
                                dataArr = new char[2];
                                dataArr[0] = responseTypeAndCode;
                                dataArr[1] = (char)length;
                                bw.write(dataArr);
                                bw.flush();

                            }
                        }
                    }
                }

                // 점주 신청
                if (firstType == 0) {
                    if (secondType == 1) {
                        if (thirdType == 4) {
                            if (code == 0) {
                                id = data;
//                                AcceptOwner acceptOwner = new AcceptOwner();
//                                acceptOwner.execute(id);
                                System.out.println("결과 메시지 출력해야 함");
                            }
                        }
                    }
                }

                if (firstType == 0) {
                    if (secondType == 1) {
                        if (thirdType == 4) {
                            if (code == 1) {
                                id = data;
//                                RejectOwner Owner = new RejectOwner();
//                                Owner.execute(id);
                                System.out.println("결과 메시지 출력해야 함");
                            }
                        }
                    }
                }

                if (firstType == 0) {
                    if (secondType == 1) {
                        if (thirdType == 0) {
                            if (code == 0) {
                                id = data;
                                AcceptStore acceptStore = new AcceptStore();
//                                acceptStore.execute(id);
                                System.out.println("결과 메시지 출력해야 함");
                            }
                        }
                    }
                }

                if (firstType == 0) {
                    if (secondType == 1) {
                        if (thirdType == 0) {
                            if (code == 1) {
                                id = data;
                                RejectStore rejectStore = new RejectStore();
//                                rejectStore.execute(id);
                                System.out.println("결과 메시지 출력해야 함");
                            }
                        }
                    }
                }

                if (firstType == 0) {
                    if (secondType == 2) {
                        if (thirdType == 1) {
                            if (code == 10) {
                                // receiveDataArr[0] = data;
                                MenuDAO menuDao = new MenuDAO();
//                                menuDao.acceptMenu(data);
                                //responseTypeAndCode = 0b0001010000000001;
                                // dataArr = new char[2];
                                // dataArr[0] = responseTypeAndCode;
                                // dataArr[1] = 0;

                                // bw.write(dataArr);
                                // bw.flush();
                            }
                        }
                    }
                }
                if (firstType == 0) {
                    if (secondType == 2) {
                        if (thirdType == 1) {
                            if (code == 11) {
                                receiveDataArr[0] = data;
                                MenuDAO menuDao = new MenuDAO();
//                                menuDao.rejectMenu(data);
                                //responseTypeAndCode = 0b0001010000000001;
                                // dataArr = new char[2];
                                // dataArr[0] = responseTypeAndCode;
                                // dataArr[1] = 0;

                                // bw.write(dataArr);
                                // bw.flush();
                            }
                        }
                    }
                }
                // 여기서 수신 파트 끝
            }
            // END
            commSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doJoin(BufferedReader br, BufferedWriter bw){
        try {
            nickname = br.readLine();

            bw.write("Welcome " + nickname + "\r\n");
            bw.flush();

            String msg = nickname + " entered the room";
            System.out.println(msg);
            cliList.sendDataToAllClients(bw, msg);

            cliList.addClient(bw);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    private void doQuit (BufferedWriter bw)
    {
        String msg = nickname + " left the room";
        System.out.println(msg);
        cliList.sendDataToAllClients(bw, msg);

        cliList.removeClient(bw);
    }
    private void doRead (BufferedWriter bw, String data)
    {
        System.out.println(nickname + " : " + data);
    }
    private void doSend (BufferedWriter bw, String data){
        String msg = nickname + ": " + data;
        cliList.sendDataToAllClients(bw, msg);
    }

}