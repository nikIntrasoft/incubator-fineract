/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.fineract.portfolio.loanaccount.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.fineract.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name = "glim_accounts", uniqueConstraints = { @UniqueConstraint(columnNames = { "account_number" }, name = "FK_glim_id")})
public class GroupLoanIndividualMonitoringAccount extends AbstractPersistableCustom<Long> {

	@Column(name = "account_number", nullable = false)
	private String accountNumber;

	@OneToMany
	private Set<Loan> childLoan;
	
	@Column(name = "principal_amount")
	private BigDecimal principalAmount;
	
	@Column(name = "child_accounts_count")
	private Long childAccountsCount;
	
	@Column(name = "accepting_child")
	private Boolean isAcceptingChild;

	
	
	private GroupLoanIndividualMonitoringAccount(String accountNumber,BigDecimal principalAmount,Long childAccountsCount,
			Boolean isAcceptingChild)
	{
		this.accountNumber=accountNumber;
		this.principalAmount=principalAmount;
		this.childAccountsCount=childAccountsCount;
		this.isAcceptingChild=isAcceptingChild;
		
	}
	
	public static GroupLoanIndividualMonitoringAccount getInstance(String accountNumber,BigDecimal principalAmount,Long childAccountsCount,
			Boolean isAcceptingChild)
	{
		return new GroupLoanIndividualMonitoringAccount(accountNumber,principalAmount,childAccountsCount,
				isAcceptingChild);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Set<Loan> getChildLoan() {
		return childLoan;
	}

	public void setChildLoan(Set<Loan> childLoan) {
		this.childLoan = childLoan;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public Long getChildAccountsCount() {
		return childAccountsCount;
	}

	public void setChildAccountsCount(Long childAccountsCount) {
		this.childAccountsCount = childAccountsCount;
	}

	public Boolean getIsAcceptingChild() {
		return isAcceptingChild;
	}

	public void setIsAcceptingChild(Boolean isAcceptingChild) {
		this.isAcceptingChild = isAcceptingChild;
	}
	
	
	




	
	

}
