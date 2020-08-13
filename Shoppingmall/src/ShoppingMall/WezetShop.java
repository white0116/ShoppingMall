package ShoppingMall;

import java.util.ArrayList;
import java.util.Scanner;

public class WezetShop {

	String title;
	String newUser;
	String newAddress;
	int newPhone;

	Stock[] DogStock = new Stock[3]; // Stock 배열안에 3가지가 들어가는 배열 생성  ex) 0, 0, 0
	Stock[] catStock = new Stock[3];
	Stock[] ectItemStock = new Stock[3];

	ArrayList<Stock> item = new ArrayList<Stock>(); // 품목명 가격 수량을 넣는 배열을 생성 

	int StockNo; 

	Scanner scan = new Scanner(System.in);

	int total = 0; // 최초 합계 금액은 0원
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNewUser() { // NewUser 클래스를 가져옴
		return newUser;
	}

	public void setNewUser(String newUser) { // 아래에서 이름 주소 번호 입력 받을 예정
		NewUser newuser = new NewUser();
		
		newuser.setName(newUser);
		newuser.setAddress(newAddress);
		newuser.setPhone(newPhone);
	}


	public void Startmessage() { // 프로그램 시작시 나오는 안내 메세지
		System.out.println("Wezet 쇼핑몰에 오신것을 환영합니다");
		System.out.println("♥ 반려동물을 사랑하는 전문 쇼핑몰   ♥");
		System.out.println("♥신규회원 가입시 3천포인트 이벤트중♥");
		System.out.println("♥여러분의 강아지♥고양이 파라다이스 ♥");
		System.out.println("Wezet 쇼핑몰에 오신것을 환영합니다");

		NewUser(); // 오픈한지 얼마 안되었기 때문에 바로 회원가입 창으로 유도
	}

	public void NewUser() { // 회원가입 창
		System.out.println("=========================");
		System.out.println("첫 방문이시군요?");
		System.out.println("회원가입을 진행합니다.");
		System.out.print("성함을 입력해 주세요 :");

		newUser = scan.next();
		System.out.println(newUser + "님 환영 합니다.");
		
		NewAddress(); // 회원가입하면서 이름 -> 주소 -> 전화번호 순서대로 입력 받음
	}

	public void NewAddress() {
		System.out.println("====================================");
		System.out.print("주소를 입력해 주세요 :");

		newAddress = scan.next();
		System.out.println(newUser + "님의 주소는 " + newAddress + "입니다.");
		
		NewPhone();
	}

	public void NewPhone() {
		System.out.println("====================================");
		System.out.printf("전화번호를 입력해 주세요 :");

		long newPhone = scan.nextLong();
		System.out.println(newUser + "님의 전화번호는 " + newPhone + "입니다.");
		System.out.println("원하시는 카테고리를 선택해 주세요");

		selectCategory();
	}

	public void AllStock() { //아이템들을 배열에 넣는 작업 

		Dog dog = new Dog("밥그릇", 15000, 15);
		dog.setInventoryname("Dog 용품");
		DogStock[0] = dog;
		dog = new Dog("마약방석", 29900, 5);
		DogStock[1] = dog;
		dog = new Dog("돌돌이", 9900, 9);
		DogStock[2] = dog;

		Cat cat = new Cat("물그릇", 9900, 10);
		cat.setInventoryname("Cat 용품");
		catStock[0] = cat;
		cat = new Cat("두부모래", 19900, 9);
		catStock[1] = cat;
		cat = new Cat("딸랑이", 3000, 19);
		catStock[2] = cat;

		ectItem ectitem = new ectItem("공기청정기", 39900, 25);
		ectitem.setInventoryname("Ect 용품");
		ectItemStock[0] = ectitem;
		ectitem = new ectItem("미니가습기", 15000, 32);
		ectItemStock[1] = ectitem;
		ectitem = new ectItem("미니청소기", 13900, 99);
		ectItemStock[2] = ectitem;
	}

	public void selectCategory() { // 기본 입력 사항 받고 카테고리 창으로 넘어옴
		System.out.println("====Wezet Category====");
		System.out.println("==== 1. Dog 물품 ====");
		System.out.println("==== 2. Cat 물품 ====");
		System.out.println("==== 3. Ect 물품 ====");
		System.out.println("====Wezet Category====");
		System.out.print("번호를 입력 바랍니다 : ");

		int categoryNo = scan.nextInt(); // 여기서 입력 받는 숫자에 따라서 카테고리가 다름

		StockList(categoryNo);
	}

	public void StockList(int categoryNo) { // 1,2,3에 따른 카테고리 선택

		if(categoryNo == 1) {
			selectStock(DogStock);
		}else if(categoryNo == 2) {
			selectStock(catStock);
		}else if(categoryNo == 3) {
			selectStock(ectItemStock);
		}else {
			System.out.println("잘못 입력 하셨습니다. 다시 입력 바랍니다."); // 입력실수를 방지해서 다시 선택할 수 있는 방법을 만듬
			selectCategory();			// 입력 실수 하면 다시 Category 선택창으로 이동
		}
	}

	public void OutCart(Stock stock) { 
		if (stock.getStocknumber() != 0) { // 장바구니에 숫자가 0이 아니면 장바구니 선택됨 

			item.add(stock);
			total = total + stock.getPrice(); // 최초 0원 + 선택된 제품금액을 더함

			System.out.printf("%s을 선택 하셨습니다. 현재 장바구니 총 금액은 %d원 입니다.\n", stock.getFullname(), stock.getPrice());
			scan.nextLine();
			checkout(); // 제품을 확인하면 결제확인창으로 이동하게끔 설정
			
			} else {
			System.out.println("==================================");
		System.out.printf("선택하신 %s 제품은 재고가 모두 소진되었습니다.\n", stock.getFullname());
		System.out.println("다른 제품을 선택해 주세요. 아무키나 눌러주세요.");
		scan.nextLine();
		scan.nextLine();
		selectCategory();
	}
}

	public void selectStock(Stock[] stock) { //카테고리를 선택 하면 해당 카테고리의 
		System.out.println("==================================");
		System.out.println("======" + stock[0].getInventoryname() + "카테고리 입니다.======"); // 카테고리 이름을 출력

		for (int i = 0; i < 3; i++) {
			System.out.printf("상품명 " + (i + 1) + " %s, 가격 : %d, 남은 재고 : %d\n", stock[i].getFullname(), 
					stock[i].getPrice(),stock[i].getStocknumber()); // 카테고리안에 배열로 설정해둔 제품들은 순서대로 출력           중간에 (i+1)은 시작이 0이기 떄문에
		}

		System.out.println("[0] : 장바구니 물품들을 계산합니다.");
		System.out.printf("선택 : ");

		StockNo = scan.nextInt(); // 

		if (StockNo == 0) { // 0번은 장바구니로 이동
			checkout();
		} else if (StockNo == 1 || StockNo == 2 || StockNo == 3) {
			OutCart(stock[StockNo - 1]);
		} else {
			System.out.println("잘못 입력 하셨습니다. 다시 선택해주세요.");
			selectStock(stock);
		}
	}

	public void checkout() {
		if (total == 0) { // 제품을 선택하지 않고 바로 장바구니로 넘어왔을경우에 카테고리 선택으로 넘어가게끔 밑에 설정
			System.out.println("==================================");
			System.out.println("장바구니가 비었습니다. 장바구니에 상품을 담아 주세요.");

			selectCategory(); //장바구니가 비었다면 카테고리 선택으로 다시 넘어감
		} else {
			printShoppingBasket(); // 실수를 제외한 올바른 입력을 했을때는 아래에 있는 결제 창으로 이동
			System.out.printf("결제하실 총 금액은 %d원 입니다. ▼ 결제 방법을 선택해 주세요  ▼\n", total);
			System.out.println("[1] : CASH");
			System.out.println("[2] : CARD");
			System.out.printf("선택  : ");

			int payment = scan.nextInt();

			if(payment == 1) {
				paymentOnCash();
			}else if(payment == 2) {
				paymentOnCard();
			}else {
				System.out.println("잘못 입력 하셨습니다. 다시 입력 해주세요.");
				printShoppingBasket1(); // 위와 같이 같은방식으로 1,2를 제외한 다른 번호를 입력하면 다시 선택 할 수 있게끔 설정
			}
		}
	}
	public void printShoppingBasket1() { // 여기서는 장바구니 목록을 보고 결제 방법 선택 
		System.out.printf("결제하실 총 금액은 %d원 입니다. ▼ 결제 방법을 선택해 주세요  ▼\n", total);
		System.out.println("[1] : CASH");
		System.out.println("[2] : CARD");
		System.out.printf("선택  : ");
		
		int payment = scan.nextInt(); // 1번은 현금 결제 2번은 카드 결제

		if(payment == 1) {
			paymentOnCash();
		}else if(payment == 2) {
			paymentOnCard();
		}else {
			System.out.println("잘못 입력 하셨습니다. 다시 입력 해주세요.");
			printShoppingBasket1(); // 입력 실수를 했을때 다시 출력함
		}
	}


	public void printShoppingBasket() {
		System.out.println("==================================");
		System.out.println("=============장바구니 목록============");
		System.out.println("순번 \t 상품 \t 가격");

		for (int i = 0; i < item.size(); i++) {
			System.out.printf("%d | %s | %d\n", i + 1, item.get(i).getFullname(), item.get(i).getPrice());
		}
		System.out.println("==================================");
		System.out.println("다른 제품도 보시겠습니까?(y/n)");  // 장바구니에 있는 목록을 출력함과 동시에 다른 제품도 볼지 말지 확인
		String choice = scan.next();
		
		switch(choice) {
		case "y" :
			selectCategory(); break;
		case "Y" : 
			selectCategory(); break;
		case "n" :
			System.out.println("결제창으로 이동합니다.");
			break;
		case "N" :
			System.out.println("결제창으로 이동합니다.");
			break;
		default :
			System.out.println("입력이 잘못 되었습니다. 다시 입력 해주세요.");
			printShoppingBasket();
		}
	}

	public void paymentOnCash() {
		System.out.printf("고객님께서 결제하셔야 될 금액은  %d 입니다.\n", total);
		System.out.println("==================================");
		System.out.println("지불 하실 금액을 입력해주세요");

		int cash = scan.nextInt(); // 지불할 금액을 입력 받음

		if (cash >= total) { // 입력 받은 금액이 총 금액 보다 클 경우 거스름돈을 출력함
			System.out.println("==================================");
			System.out.printf("%d원을 지불 하셨습니다. 거스름돈은 %d입니다.\n", cash, cash-total);
			System.out.println("계산이 완료되었습니다. 감사합니다.");
		} else { // 입력받은 금액이 적으면 차액지불창으로 이동
			System.out.println("==================================");
			System.out.printf("%d원이 모자랍니다. 추가로 넣어주세요.\n", total - cash);

			differencePayment(cash); //차액 지불 메소드
		}
	}

	public void differencePayment(int cash) {
		System.out.println("차액 지불 : ");

		int difference = scan.nextInt();

		if (difference == total - cash) {
			System.out.println("==================================");
			System.out.println("계산이 완료되었습니다. 감사합니다.");
		} else if (difference < total - cash) {
			total = (total - cash) - difference;

			System.out.println("==================================");
			System.out.printf("%d원을 더 입력 하셔야 합니다.\n", total);

			differencePayment(total);
		} else {
			System.out.println("==================================");
			System.out.printf("%d원을 지불 하셨습니다. 거스름돈은 %d입니다.\n", difference, difference - (total - cash));
			System.out.println("계산이 완료 되었습니다. 감사합니다.");
			System.out.println("프로그램 종료");
		}
	}

	public void paymentOnCard() {
			System.out.println("==================================");
			System.out.println("카드번호를 입력해 주세요");
			long cardnumber = scan.nextLong(); // 카드 번호 입력
			scan.nextLine();
			paymentOnCard1();
	}
	public void paymentOnCard1() {
			
			System.out.println("영수증이 필요하신가요?(y/n)");
			System.out.printf("선택  : ");
			
			String isNeedRecipt = scan.next();
			
			switch(isNeedRecipt){
				case "y" :

				System.out.println("영수증 발급이 완료 되었습니다. 감사합니다.");
				System.out.println("프로그램 종료"); break;
				case "Y" :

				System.out.println("영수증 발급이 완료 되었습니다. 감사합니다.");
				System.out.println("프로그램 종료"); break;
				case "n" :

				System.out.println("계산이 완료되었습니다. 감사합니다.");
				System.out.println("프로그램 종료"); break;
				case "N" :

				System.out.println("계산이 완료되었습니다. 감사합니다.");
				System.out.println("프로그램 종료"); break;
				default:

					System.out.println("입력이 잘못 되었습니다. 다시 선택해 주세요");
					paymentOnCard1();
		}
	}
}
