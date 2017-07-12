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


package org.apache.fineract.portfolio.loanaccount.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.fineract.portfolio.loanaccount.data.GroupLoanIndividualMonitoringAccountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class GLIMAccountInfoReadPlatformServiceImpl implements GLIMAccountInfoReadPlatformService
{

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	
	@Autowired
	public GLIMAccountInfoReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
	
	private static final class GLIMFieldsMapper implements RowMapper<GroupLoanIndividualMonitoringAccountData> {
		public String schema() {
			return "glim.account_number as accountNumber, glim.principal_amount as principalAmount,glim.child_accounts_count as childAccountsCount,"
					+"glim.accepting_child as isAcceptingChild from glim_accounts glim;";
		}

		@Override
		public GroupLoanIndividualMonitoringAccountData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {

		final String accountNumber=rs.getString("accountNumber");
		
		final String childAccountNumber=rs.getString("childAccountNumber");
		
		final Long childAccountsCount=rs.getLong("childAccountsCount");
		
		final Boolean isAcceptingChild=rs.getBoolean("isAcceptingChild");
		
		final BigDecimal principalAmount=rs.getBigDecimal("principalAmount");

			return new GroupLoanIndividualMonitoringAccountData(accountNumber,childAccountNumber,principalAmount,
					childAccountsCount,isAcceptingChild);
			
			

		}
	}
	
	
	@Override
	public  GroupLoanIndividualMonitoringAccountData findCurrentChildAccount(String parentAccountId) {
		this.context.authenticatedUser();

		final GLIMFieldsMapper rm = new GLIMFieldsMapper();
		final String sql = "select " + rm.schema() + " where glim.glim_parent_account_id=? and glim.isCurrentChildAccount=true";

		return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { parentAccountId });
	}
	
	@Override
	public  GroupLoanIndividualMonitoringAccountData findCurrentChildAccount() {
		this.context.authenticatedUser();

		final GLIMFieldsMapper rm = new GLIMFieldsMapper();
		final String sql = "select " + rm.schema() + " where glim.isCurrentChildAccount=true";

		return this.jdbcTemplate.queryForObject(sql, rm, new Object[] {  });
	}
	
	
	
	@Override
	public  GroupLoanIndividualMonitoringAccountData findParentOfChildAccount(String glim_child_account_id) {
		this.context.authenticatedUser();

		final GLIMFieldsMapper rm = new GLIMFieldsMapper();
		final String sql = "select " + rm.schema() + " where glim.glim_child_account_id=? and glim.isParentAccount=true";

		return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { glim_child_account_id });
	}
}
