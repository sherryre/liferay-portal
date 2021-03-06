/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.lists.web.asset;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.mapping.util.DDMBeanCopyUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portlet.asset.model.BaseDDMFormValuesReader;
import com.liferay.portlet.dynamicdatamapping.storage.DDMFormValues;

/**
 * @author Adolfo Pérez
 */
public class DDLRecordDDMFormValuesReader extends BaseDDMFormValuesReader {

	public DDLRecordDDMFormValuesReader(DDLRecord ddlRecord) {
		_ddlRecord = ddlRecord;
	}

	@Override
	public DDMFormValues getDDMFormValues() throws PortalException {
		return DDMBeanCopyUtil.copyDDMFormValues(_ddlRecord.getDDMFormValues());
	}

	private final DDLRecord _ddlRecord;

}