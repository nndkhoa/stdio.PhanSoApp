package entities;

import java.util.Scanner;

public class PhanSo {
  private int tuSo, mauSo;

  public int getTuSo() {
    return this.tuSo;
  }

  public int getMauSo() {
    return this.mauSo;
  }

  // public String getDisplayString() {
  //   return String.format("%d/%d", this.tuSo, this.mauSo);
  // }

  @Override
  public String toString() {
    return String.format("%d/%d", this.tuSo, this.mauSo);
  }

  public void print() {
    System.out.format("%d/%d\n", this.tuSo, this.mauSo);
  }

  public void input() {
    Scanner sc = new Scanner(System.in);
    System.out.print("TuSo: ");
    this.tuSo = sc.nextInt();
    System.out.print("MauSo: ");
    this.mauSo = sc.nextInt();
  }

  // 8,12 <-> 8, 4 <-> 4, 4 ==> 4
  // 6, 8 <-> 6, 2 <-> 4, 2 <-> 2, 2 ==> 2
  private static int gcd(int a, int b) {
    if (a == b)
      return a;

    if (a > b) {
      return gcd(a - b, b);
    }

    return gcd(a, b - a);
  }

  public boolean isReducible() {
    int gcd = gcd(Math.abs(this.tuSo), Math.abs(this.mauSo));
    if (gcd == 1) {
      return false;
    }

    return true;
  }

  public boolean isPositive() {
    if (this.tuSo == 0)
      return false;

    if (this.tuSo > 0 && this.mauSo > 0)
      return true;

    if (this.tuSo < 0 && this.mauSo < 0)
      return true;

    return false;
  }

  public void reduce() {
    int gcd = gcd(Math.abs(this.tuSo), Math.abs(this.mauSo));
    this.tuSo /= gcd;
    this.mauSo /= gcd;
  }

  public int compareTo(PhanSo another) {
    long tmp1 = this.tuSo * another.mauSo;
    long tmp2 = this.mauSo * another.tuSo;

    if (tmp1 > tmp2)
      return 1; // this > another

    if (tmp1 < tmp2)
      return -1; // this < another

    return 0; // this == another
  }

  public void plus(PhanSo another) {
    int tmp_ts = this.tuSo * another.mauSo + this.mauSo * another.tuSo;
    int tmp_ms = this.mauSo * another.mauSo;
    this.tuSo = tmp_ts;
    this.mauSo = tmp_ms;
    this.reduce();
  }

  public static PhanSo sum(PhanSo ps1, PhanSo ps2) {
    PhanSo ret = new PhanSo();
    ret.tuSo = ps1.tuSo * ps2.mauSo + ps1.mauSo * ps2.tuSo;
    ret.mauSo = ps1.mauSo * ps2.mauSo;
    ret.reduce();

    return ret;
  }

  // operation
  // dùng hàm bắt buộc tạo object/instance thuộc lớp
  // có thể sử dụng được các field/data thông qua từ khoá this

  // static operation
  // dùng hàm không cần tạo object/instance thuộc lớp
  // hàm static không sử dụng được các field/data của lớp PhanSo

  public static PhanSo parseFromString(String str) {
    String[] parts = str.split("/");

    PhanSo ps = new PhanSo();
    ps.tuSo = Integer.parseInt(parts[0]);
    ps.mauSo = Integer.parseInt(parts[1]);
    return ps;
  }
}
