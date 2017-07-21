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



-- permissions added

 INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('portfolio', 'APPROVE_GLIM_LOAN', 'GLIMLOAN', 'APPROVE', 0);
 INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('portfolio', 'DISBURSEGLIM_LOAN', 'GLIMLOAN', 'DISBURSE', 0);
 INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('portfolio', 'REPAYMENT_GLIMLOAN', 'GLIMLOAN', 'REPAYMENT', 0);
 INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('portfolio', 'UNDODISBURSAL_GLIMLOAN', 'GLIMLOAN', 'UNDODISBURSAL', 0);
 INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('portfolio', 'UNDOAPPROVAL_GLIMLOAN', 'GLIMLOAN', 'UNDOAPPROVAL', 0);
 
 -- change m_loan table
 alter table `m_loan` add CONSTRAINT `FK_glim_id` FOREIGN KEY  (`glim_id`) REFERENCES `glim_accounts` (`id`);
 
 -- new table glim_accounts added
 
 CREATE TABLE `glim_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL DEFAULT '0',
  `account_number` varchar(50) NOT NULL,
  `principal_amount` bigint(20) NOT NULL DEFAULT '0',
  `child_accounts_count` int(11) NOT NULL,
  `accepting_child` tinyint(4) NOT NULL DEFAULT '0',
  `loan_status_id` smallint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `glim_account_no_UNIQUE` (`account_number`),
  KEY `FK_group_id` (`group_id`),
  CONSTRAINT `FK_group_id` FOREIGN KEY (`group_id`) REFERENCES `m_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
        
        