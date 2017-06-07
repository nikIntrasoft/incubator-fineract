--
-- Licensed to the Apache Software Foundation (ASF) under one
-- or more contributor license agreements. See the NOTICE file
-- distributed with this work for additional information
-- regarding copyright ownership. The ASF licenses this file
-- to you under the Apache License, Version 2.0 (the
-- "License"); you may not use this file except in compliance
-- with the License. You may obtain a copy of the License at
--
-- http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing,
-- software distributed under the License is distributed on an
-- "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
-- KIND, either express or implied. See the License for the
-- specific language governing permissions and limitations
-- under the License.
--

DROP TABLE IF EXISTS `m_group_loan_individual_monitoring`;

CREATE TABLE `m_group_loan_individual_monitoring` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`loan_id` BIGINT(20) NOT NULL,
	`loanpurpose_cv_id` INT(20) NULL DEFAULT NULL,
	`proposed_amount` DECIMAL(19,6) NULL DEFAULT NULL,
	`approved_amount` DECIMAL(19,6) NULL DEFAULT NULL,
	`disbursed_amount` DECIMAL(19,6) NULL DEFAULT NULL,
	`is_selected` tinyint(1) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`),
	INDEX `FK_m_group_loan_individual_monitoring_m_client` (`client_id`),
	INDEX `FK_m_group_loan_individual_monitoring_m_loan` (`loan_id`),
	INDEX `FK_m_group_loan_individual_monitoring_m_code_value` (`loanpurpose_cv_id`),
	CONSTRAINT `FK_m_group_loan_individual_monitoring_m_client` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK_m_group_loan_individual_monitoring_m_code_value` FOREIGN KEY (`loanpurpose_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK_m_group_loan_individual_monitoring_m_loan` FOREIGN KEY (`loan_id`) REFERENCES `m_loan` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
