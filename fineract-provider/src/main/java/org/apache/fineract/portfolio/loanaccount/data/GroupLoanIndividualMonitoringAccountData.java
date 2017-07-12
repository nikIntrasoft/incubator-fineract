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

package org.apache.fineract.portfolio.loanaccount.data;

import java.math.BigDecimal;

public class GroupLoanIndividualMonitoringAccountData 
{
	
	private final String accountNumber;
	
	private final String childAccountNumber;
	
	private final BigDecimal principalAmountAtParentAccount;
	
	private final Long ChildCount;
	
	private final Boolean isAcceptingChilds;
	

	public GroupLoanIndividualMonitoringAccountData(final String parentAccountId,final String childAccountId,
			final BigDecimal totalLoanOnParentAccount,final Long subChildCount,final Boolean isCurrentChildAccount)
	{
		this.accountNumber=parentAccountId;
		this.childAccountNumber=childAccountId;
		this.ChildCount=subChildCount;
		this.isAcceptingChilds=isCurrentChildAccount;
		this.principalAmountAtParentAccount=totalLoanOnParentAccount;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public String getChildAccountNumber() {
		return childAccountNumber;
	}


	public BigDecimal getPrincipalAmountAtParentAccount() {
		return principalAmountAtParentAccount;
	}


	public Long getChildCount() {
		return ChildCount;
	}


	public Boolean getIsAcceptingChilds() {
		return isAcceptingChilds;
	}
	
	
	

	

	
	


	
	
	
	
}
