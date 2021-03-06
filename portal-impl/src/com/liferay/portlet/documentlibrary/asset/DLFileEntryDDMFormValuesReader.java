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

package com.liferay.portlet.documentlibrary.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.BaseDDMFormValuesReader;
import com.liferay.portlet.documentlibrary.model.DLFileEntryMetadata;
import com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.DDMStructureManagerUtil;
import com.liferay.portlet.dynamicdatamapping.StorageEngineManagerUtil;
import com.liferay.portlet.dynamicdatamapping.storage.DDMFormValues;

import java.util.List;

/**
 * @author Adolfo Pérez
 */
public class DLFileEntryDDMFormValuesReader extends BaseDDMFormValuesReader {

	public DLFileEntryDDMFormValuesReader(
		FileEntry dlFileEntry, FileVersion fileVersion) {

		_fileEntry = dlFileEntry;
		_fileVersion = fileVersion;
	}

	@Override
	public DDMFormValues getDDMFormValues() throws PortalException {
		DLFileEntryMetadata dlFileEntryMetadata = getDLFileEntryMetadata();

		if (dlFileEntryMetadata == null) {
			return new DDMFormValues(null);
		}

		return StorageEngineManagerUtil.getDDMFormValues(
			dlFileEntryMetadata.getDDMStorageId());
	}

	protected DLFileEntryMetadata getDLFileEntryMetadata() {
		List<DDMStructure> ddmStructures =
			DDMStructureManagerUtil.getClassStructures(
				_fileEntry.getCompanyId(),
				PortalUtil.getClassNameId(DLFileEntryMetadata.class));

		DLFileEntryMetadata dlFileEntryMetadata = null;

		for (DDMStructure ddmStructure : ddmStructures) {
			dlFileEntryMetadata =
				DLFileEntryMetadataLocalServiceUtil.fetchFileEntryMetadata(
					ddmStructure.getStructureId(),
					_fileVersion.getFileVersionId());

			if (dlFileEntryMetadata != null) {
				break;
			}
		}

		return dlFileEntryMetadata;
	}

	private final FileEntry _fileEntry;
	private final FileVersion _fileVersion;

}