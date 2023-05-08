package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class FinancialYearRevenue implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger april = BigInteger.ZERO;

	private BigInteger may = BigInteger.ZERO;

	private BigInteger june = BigInteger.ZERO;

	private BigInteger q1FYA = BigInteger.ZERO;

	private BigInteger q1FYB = BigInteger.ZERO;

	private BigInteger q1FYP = BigInteger.ZERO;

	private BigInteger july = BigInteger.ZERO;

	private BigInteger august = BigInteger.ZERO;

	private BigInteger september = BigInteger.ZERO;

	private BigInteger q2FYA = BigInteger.ZERO;

	private BigInteger q2FYB = BigInteger.ZERO;

	private BigInteger q2FYP = BigInteger.ZERO;

	private BigInteger october = BigInteger.ZERO;

	private BigInteger november = BigInteger.ZERO;

	private BigInteger december = BigInteger.ZERO;

	private BigInteger q3FYA = BigInteger.ZERO;

	private BigInteger q3FYB = BigInteger.ZERO;

	private BigInteger q3FYP = BigInteger.ZERO;

	private BigInteger january = BigInteger.ZERO;

	private BigInteger february = BigInteger.ZERO;

	private BigInteger march = BigInteger.ZERO;

	private BigInteger q4FYA = BigInteger.ZERO;

	private BigInteger q4FYB = BigInteger.ZERO;

	private BigInteger q4FYP = BigInteger.ZERO;

	private BigInteger fyP = BigInteger.ZERO;

	private BigInteger fyB = BigInteger.ZERO;

	private BigInteger diffFY = BigInteger.ZERO;

	public FinancialYearRevenue() {
	
	}

	public FinancialYearRevenue(BigInteger april, BigInteger may, BigInteger june, BigInteger q1fya, BigInteger q1fyb,
			BigInteger q1fyp, BigInteger july, BigInteger august, BigInteger september, BigInteger q2fya,
			BigInteger q2fyb, BigInteger q2fyp, BigInteger october, BigInteger november, BigInteger december,
			BigInteger q3fya, BigInteger q3fyb, BigInteger q3fyp, BigInteger january, BigInteger february,
			BigInteger march, BigInteger q4fya, BigInteger q4fyb, BigInteger q4fyp, BigInteger fyP, BigInteger fyB,
			BigInteger diffFY) {
		super();
		this.april = april;
		this.may = may;
		this.june = june;
		q1FYA = q1fya;
		q1FYB = q1fyb;
		q1FYP = q1fyp;
		this.july = july;
		this.august = august;
		this.september = september;
		q2FYA = q2fya;
		q2FYB = q2fyb;
		q2FYP = q2fyp;
		this.october = october;
		this.november = november;
		this.december = december;
		q3FYA = q3fya;
		q3FYB = q3fyb;
		q3FYP = q3fyp;
		this.january = january;
		this.february = february;
		this.march = march;
		q4FYA = q4fya;
		q4FYB = q4fyb;
		q4FYP = q4fyp;
		this.fyP = fyP;
		this.fyB = fyB;
		this.diffFY = diffFY;
	}

	public BigInteger getApril() {
		return april;
	}

	public void setApril(BigInteger april) {
		this.april = april;
	}

	public BigInteger getMay() {
		return may;
	}

	public void setMay(BigInteger may) {
		this.may = may;
	}

	public BigInteger getJune() {
		return june;
	}

	public void setJune(BigInteger june) {
		this.june = june;
	}

	public BigInteger getQ1FYA() {
		return q1FYA;
	}

	public void setQ1FYA(BigInteger q1fya) {
		q1FYA = q1fya;
	}

	public BigInteger getQ1FYB() {
		return q1FYB;
	}

	public void setQ1FYB(BigInteger q1fyb) {
		q1FYB = q1fyb;
	}

	public BigInteger getQ1FYP() {
		return q1FYP;
	}

	public void setQ1FYP(BigInteger q1fyp) {
		q1FYP = q1fyp;
	}

	public BigInteger getJuly() {
		return july;
	}

	public void setJuly(BigInteger july) {
		this.july = july;
	}

	public BigInteger getAugust() {
		return august;
	}

	public void setAugust(BigInteger august) {
		this.august = august;
	}

	public BigInteger getSeptember() {
		return september;
	}

	public void setSeptember(BigInteger september) {
		this.september = september;
	}

	public BigInteger getQ2FYA() {
		return q2FYA;
	}

	public void setQ2FYA(BigInteger q2fya) {
		q2FYA = q2fya;
	}

	public BigInteger getQ2FYB() {
		return q2FYB;
	}

	public void setQ2FYB(BigInteger q2fyb) {
		q2FYB = q2fyb;
	}

	public BigInteger getQ2FYP() {
		return q2FYP;
	}

	public void setQ2FYP(BigInteger q2fyp) {
		q2FYP = q2fyp;
	}

	public BigInteger getOctober() {
		return october;
	}

	public void setOctober(BigInteger october) {
		this.october = october;
	}

	public BigInteger getNovember() {
		return november;
	}

	public void setNovember(BigInteger november) {
		this.november = november;
	}

	public BigInteger getDecember() {
		return december;
	}

	public void setDecember(BigInteger december) {
		this.december = december;
	}

	public BigInteger getQ3FYA() {
		return q3FYA;
	}

	public void setQ3FYA(BigInteger q3fya) {
		q3FYA = q3fya;
	}

	public BigInteger getQ3FYB() {
		return q3FYB;
	}

	public void setQ3FYB(BigInteger q3fyb) {
		q3FYB = q3fyb;
	}

	public BigInteger getQ3FYP() {
		return q3FYP;
	}

	public void setQ3FYP(BigInteger q3fyp) {
		q3FYP = q3fyp;
	}

	public BigInteger getJanuary() {
		return january;
	}

	public void setJanuary(BigInteger january) {
		this.january = january;
	}

	public BigInteger getFebruary() {
		return february;
	}

	public void setFebruary(BigInteger february) {
		this.february = february;
	}

	public BigInteger getMarch() {
		return march;
	}

	public void setMarch(BigInteger march) {
		this.march = march;
	}

	public BigInteger getQ4FYA() {
		return q4FYA;
	}

	public void setQ4FYA(BigInteger q4fya) {
		q4FYA = q4fya;
	}

	public BigInteger getQ4FYB() {
		return q4FYB;
	}

	public void setQ4FYB(BigInteger q4fyb) {
		q4FYB = q4fyb;
	}

	public BigInteger getQ4FYP() {
		return q4FYP;
	}

	public void setQ4FYP(BigInteger q4fyp) {
		q4FYP = q4fyp;
	}

	public BigInteger getFyP() {
		return fyP;
	}

	public void setFyP(BigInteger fyP) {
		this.fyP = fyP;
	}

	public BigInteger getFyB() {
		return fyB;
	}

	public void setFyB(BigInteger fyB) {
		this.fyB = fyB;
	}

	public BigInteger getDiffFY() {
		return diffFY;
	}

	public void setDiffFY(BigInteger diffFY) {
		this.diffFY = diffFY;
	}


}
