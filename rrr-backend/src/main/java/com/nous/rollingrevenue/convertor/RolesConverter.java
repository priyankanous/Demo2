package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.AccountPermission;
import com.nous.rollingrevenue.model.AdministrationCommonPermission;
import com.nous.rollingrevenue.model.AdministrationPermission;
import com.nous.rollingrevenue.model.AnnualTargetEntryPermission;
import com.nous.rollingrevenue.model.ArchiveWisePermission;
import com.nous.rollingrevenue.model.BDMMeetingsPermission;
import com.nous.rollingrevenue.model.BDMPermission;
import com.nous.rollingrevenue.model.BusinessTypePermission;
import com.nous.rollingrevenue.model.BusinessTypeViewPermission;
import com.nous.rollingrevenue.model.BusinessUnitPermission;
import com.nous.rollingrevenue.model.BusinessUnitWiseViewPermission;
import com.nous.rollingrevenue.model.COCPracticePermission;
import com.nous.rollingrevenue.model.CalendarCommonPermission;
import com.nous.rollingrevenue.model.CalendarPermission;
import com.nous.rollingrevenue.model.ClientWiseViewPermission;
import com.nous.rollingrevenue.model.CurrencyPermission;
import com.nous.rollingrevenue.model.DashboardPermission;
import com.nous.rollingrevenue.model.ExplicitPermission;
import com.nous.rollingrevenue.model.FinancialYearPermission;
import com.nous.rollingrevenue.model.FortnightlyMeetingsPermission;
import com.nous.rollingrevenue.model.GlobalLeaveLossFactorPermission;
import com.nous.rollingrevenue.model.HolidayCalendarPermission;
import com.nous.rollingrevenue.model.InvoiceDataUploadPermission;
import com.nous.rollingrevenue.model.LocationPermission;
import com.nous.rollingrevenue.model.NotificationConfigurationPermission;
import com.nous.rollingrevenue.model.OpportunityPermission;
import com.nous.rollingrevenue.model.PricingTypePermission;
import com.nous.rollingrevenue.model.ProbabilityTypePermission;
import com.nous.rollingrevenue.model.ProbabilityTypeViewPermission;
import com.nous.rollingrevenue.model.RegionPermission;
import com.nous.rollingrevenue.model.RegionWiseViewPermission;
import com.nous.rollingrevenue.model.ReportsCommonPermission;
import com.nous.rollingrevenue.model.ReportsPermission;
import com.nous.rollingrevenue.model.RevenuePermission;
import com.nous.rollingrevenue.model.ReviewandPublishPermission;
import com.nous.rollingrevenue.model.RoleUserPermission;
import com.nous.rollingrevenue.model.Roles;
import com.nous.rollingrevenue.model.RolesPermission;
import com.nous.rollingrevenue.model.RollingRevenueEntryPermission;
import com.nous.rollingrevenue.model.SBUClientViewPermission;
import com.nous.rollingrevenue.model.SBUHeadPermission;
import com.nous.rollingrevenue.model.SBUPermission;
import com.nous.rollingrevenue.model.SettingsCommonPermission;
import com.nous.rollingrevenue.model.SettingsPermission;
import com.nous.rollingrevenue.model.WorkOrderPermission;
import com.nous.rollingrevenue.vo.AccountPermissionVO;
import com.nous.rollingrevenue.vo.AdministrationPermissionVO;
import com.nous.rollingrevenue.vo.AnnualTargetEntryPermissionVO;
import com.nous.rollingrevenue.vo.ArchiveWisePermissionVO;
import com.nous.rollingrevenue.vo.BDMMeetingPermissionVO;
import com.nous.rollingrevenue.vo.BDMPermissionVO;
import com.nous.rollingrevenue.vo.BusinessTypePermissionVO;
import com.nous.rollingrevenue.vo.BusinessTypeViewPermissionVO;
import com.nous.rollingrevenue.vo.BusinessUnitPermissionVO;
import com.nous.rollingrevenue.vo.BusinessUnitViewPermissionVO;
import com.nous.rollingrevenue.vo.CalendarPermissionVO;
import com.nous.rollingrevenue.vo.ClientWisePermissionVO;
import com.nous.rollingrevenue.vo.CocPracticePermissionVO;
import com.nous.rollingrevenue.vo.CommonAdministrationPermissionVO;
import com.nous.rollingrevenue.vo.CommonCalendarPermissionVO;
import com.nous.rollingrevenue.vo.CurrencyPermissionVO;
import com.nous.rollingrevenue.vo.DashboardPermissionVO;
import com.nous.rollingrevenue.vo.ExplicitPermissionVO;
import com.nous.rollingrevenue.vo.FinancialYearPermissionVO;
import com.nous.rollingrevenue.vo.FortnightlyMeetingPermissionVO;
import com.nous.rollingrevenue.vo.GlobalLeaveLassFactorPermissionVO;
import com.nous.rollingrevenue.vo.HolidayCalendarPermissionVO;
import com.nous.rollingrevenue.vo.InvoiceDataUploadPermissionVO;
import com.nous.rollingrevenue.vo.LocationPermissionVO;
import com.nous.rollingrevenue.vo.NotificationConfigPermissionVO;
import com.nous.rollingrevenue.vo.OpportunityPermissionVO;
import com.nous.rollingrevenue.vo.PricingTypePermissionVO;
import com.nous.rollingrevenue.vo.ProbabilityPermissionVO;
import com.nous.rollingrevenue.vo.ProbabilityTypePermissionVO;
import com.nous.rollingrevenue.vo.RegionPermissionVO;
import com.nous.rollingrevenue.vo.RegionViewPermissionVO;
import com.nous.rollingrevenue.vo.ReportsCommonPermissionVO;
import com.nous.rollingrevenue.vo.ReportsPermissionVO;
import com.nous.rollingrevenue.vo.RevenuePermissionVO;
import com.nous.rollingrevenue.vo.ReviewandPublishPermissionVO;
import com.nous.rollingrevenue.vo.RoleUserAssignmentPermissionVO;
import com.nous.rollingrevenue.vo.RolesPermissionVO;
import com.nous.rollingrevenue.vo.RolesVO;
import com.nous.rollingrevenue.vo.RollingRevenueEntryPermissionVO;
import com.nous.rollingrevenue.vo.SBUClientViewPermissionVO;
import com.nous.rollingrevenue.vo.SBUHeadPermissionVO;
import com.nous.rollingrevenue.vo.SBUPermissionVO;
import com.nous.rollingrevenue.vo.SettingsPermissionVO;
import com.nous.rollingrevenue.vo.WorkOrderPermissionVO;

@Component
public class RolesConverter {

	private RolesConverter() {
		super();
	}

	/**
	 * Convert RolesVO to Roles
	 * 
	 * @param RolesVO
	 * @return Roles
	 */
	public static Roles convertRolesVOToRoles(RolesVO rolesVO) {
		Roles roles = new Roles();
		if (rolesVO != null) {
			roles.setRoleId(rolesVO.getRoleId());
			roles.setRoleName(rolesVO.getRoleName());
			roles.setRoleDisplayName(rolesVO.getRoleDisplayName());
			roles.setSelectAll(rolesVO.isSelectAllPermissions());
		}
		return roles;
	}

	/**
	 * Convert Roles to RolesVO
	 * 
	 * @param Roles
	 * @return RolesVO
	 */
	public static RolesVO convertRolesToRolesVO(Roles roles) {
		RolesVO rolesVO = new RolesVO();
		rolesVO.setRoleId(roles.getRoleId());
		rolesVO.setRoleName(roles.getRoleName());
		rolesVO.setRoleDisplayName(roles.getRoleDisplayName());
		rolesVO.setSelectAllPermissions(roles.isSelectAll());
		rolesVO.setActive(roles.isActive());

		// Dashboard Permission
		DashboardPermissionVO dashVO = new DashboardPermissionVO();
		DashboardPermission dashboardPermission = roles.getDashboardPermission();
		dashVO.setDashboardPermissionId(dashboardPermission.getDashboardPermissionId());
		dashVO.setDashboardPermissionAll(dashboardPermission.isDashboardPermissionAll());
		dashVO.setEditDashboardRequired(dashboardPermission.isEditDashboardRequired());
		dashVO.setReadDashboardRequired(dashboardPermission.isReadDashboardRequired());
		dashVO.setActive(dashboardPermission.isActive());
		rolesVO.setDashboardPermissionVO(dashVO);

		// Revenue Permission
		if (roles.getRevenuePermission() != null) {
			RevenuePermissionVO revenueVO = new RevenuePermissionVO();
			RevenuePermission revenuePermission = roles.getRevenuePermission();
			revenueVO.setRevenuePermissionId(revenuePermission.getRevenuePermissionId());
			revenueVO.setRevenuePermissionAll(revenuePermission.isRevenuePermissionAll());
			revenueVO.setViewAllEntries(revenuePermission.isViewAllEntriesRequired());
			if (revenuePermission.getRollingRevenueEntryPermission() != null) {
				RollingRevenueEntryPermissionVO revenueEntryVO = new RollingRevenueEntryPermissionVO();
				RollingRevenueEntryPermission rollingRevenueEntry = revenuePermission
						.getRollingRevenueEntryPermission();
				revenueEntryVO
						.setRollingrevenueEntryPermissionId(rollingRevenueEntry.getRollingrevenueEntryPermissionId());
				revenueEntryVO
						.setRollingrevenueEntryPermissionAll(rollingRevenueEntry.isRollingRevenueEntryPermissionAll());
				revenueEntryVO.setViewAllEntriesRequired(rollingRevenueEntry.isViewAllEntriesRequired());
				revenueEntryVO.setAddRevenueEntryRequired(rollingRevenueEntry.isAddRevenueEntryRequired());
				revenueEntryVO.setEditRevenueEntryRequired(rollingRevenueEntry.isEditRevenueEntryRequired());
				revenueEntryVO.setDeleteRevenueEntryRequired(rollingRevenueEntry.isDeleteRevenueEntryRequired());
				revenueEntryVO.setCopyRevenueEntryRequired(rollingRevenueEntry.isCopyRevenueEntryRequired());
				revenueEntryVO.setSubmitRevenueEntryRequired(rollingRevenueEntry.isSubmitRevenueEntryRequired());
				revenueEntryVO.setExportRequired(rollingRevenueEntry.isExportRequired());
				revenueEntryVO.setActive(rollingRevenueEntry.isActive());
				revenueVO.setRollingRevenueEntryPermissionVO(revenueEntryVO);
			}
			if (revenuePermission.getInvoiceDataUploadPermission() != null) {
				InvoiceDataUploadPermissionVO invoiceUploadVO = new InvoiceDataUploadPermissionVO();
				InvoiceDataUploadPermission invoiceDataUpload = revenuePermission.getInvoiceDataUploadPermission();
				invoiceUploadVO.setInvoiceDataUploadPermissionId(invoiceDataUpload.getInvoiceDataUploadPermissionId());
				invoiceUploadVO.setInvoiceDataUploadPermissionAll(invoiceDataUpload.isInvoiceDataUploadPermissionAll());
				invoiceUploadVO.setViewListRequired(invoiceDataUpload.isViewListRequired());
				invoiceUploadVO.setUploadDataRequired(invoiceDataUpload.isUploadDataRequired());
				invoiceUploadVO.setEditableRequired(invoiceDataUpload.isEditOrReupload());
				invoiceUploadVO.setDeleteRequired(invoiceDataUpload.isDeleteDataRequired());
				invoiceUploadVO.setActive(invoiceDataUpload.isActive());
				revenueVO.setInvoiceDataUploadPermissionVO(invoiceUploadVO);
			}
			if (revenuePermission.getReviewandPublishPermission() != null) {
				ReviewandPublishPermissionVO reviewandPublishVO = new ReviewandPublishPermissionVO();
				ReviewandPublishPermission reviewandPublish = revenuePermission.getReviewandPublishPermission();
				reviewandPublishVO.setReviewandPublishPermissionId(reviewandPublish.getReviewandPublishPermissionId());
				reviewandPublishVO.setReviewandPublishPermissionAll(reviewandPublish.isReviewAndPublishPermissionAll());
				reviewandPublishVO.setViewRequired(reviewandPublish.isViewRequired());
				reviewandPublishVO.setReviewAndSubmitRequired(reviewandPublish.isReviewAndSubmitRequired());
				reviewandPublishVO.setSaveRequired(reviewandPublish.isSaveRequired());
				reviewandPublishVO.setCopyRequired(reviewandPublish.isCopyRequired());
				reviewandPublishVO.setEditRequired(reviewandPublish.isEditRequired());
				reviewandPublishVO.setPublishRequired(reviewandPublish.isPublishRequired());
				reviewandPublishVO.setSaveRecipientsRequired(reviewandPublish.isSaveRecipientsRequired());
				reviewandPublishVO.setActive(reviewandPublish.isActive());
				revenueVO.setReviewandPublishPermissionVO(reviewandPublishVO);
			}
			rolesVO.setRevenuePermissionVO(revenueVO);
		}

		// Reports Permission
		if (roles.getReportsPermission() != null) {
			ReportsPermissionVO reportsVO = new ReportsPermissionVO();
			ReportsPermission reportsPermission = roles.getReportsPermission();
			reportsVO.setReportsPermissionId(reportsPermission.getReportsPermissionId());
			reportsVO.setReportsPermissionsAll(reportsPermission.isReportsPermissionAll());
			reportsVO.setViewAllEntries(reportsPermission.isViewAllEntriesRequired());
			// Business Type View
			if (reportsPermission.getBusinessTypeViewPermission() != null) {
				BusinessTypeViewPermissionVO businessTypeViewPermissionVO = new BusinessTypeViewPermissionVO();
				BusinessTypeViewPermission businessTypeView = reportsPermission.getBusinessTypeViewPermission();
				businessTypeViewPermissionVO
						.setBusinessTypeViewPermissionId(businessTypeView.getBusinessTypeViewPermissionId());
				businessTypeViewPermissionVO
						.setBusinessTypeViewPermissionAll(businessTypeView.isBusinessTypeViewPermissionAll());
				if (businessTypeView.getReportsCommonPermission() != null) {
					businessTypeViewPermissionVO.setReportsCommonPermissionVO(
							setReportsCommonPermission(businessTypeView.getReportsCommonPermission()));
				}
				reportsVO.setBusinessTypeViewPermissionVO(businessTypeViewPermissionVO);
			}
			// sbu client view
			if (reportsPermission.getSbuClientViewPermission() != null) {
				SBUClientViewPermissionVO sbuClientViewPermissionVO = new SBUClientViewPermissionVO();
				SBUClientViewPermission sbuClientView = reportsPermission.getSbuClientViewPermission();
				sbuClientViewPermissionVO.setSbuClientViewPermissionId(sbuClientView.getSbuClientViewPermissionId());
				sbuClientViewPermissionVO.setSbuClientViewPermissionAll(sbuClientView.isSbuClientViewPermissionAll());
				if (sbuClientView.getReportsCommonPermission() != null) {
					sbuClientViewPermissionVO.setReportsCommonPermissionVO(
							setReportsCommonPermission(sbuClientView.getReportsCommonPermission()));
				}
				reportsVO.setSbuClientViewPermissionVO(sbuClientViewPermissionVO);
			}
			// Probability type view
			if (reportsPermission.getProbabilityTypeViewPermission() != null) {
				ProbabilityTypePermissionVO probabilityTypeVO = new ProbabilityTypePermissionVO();
				ProbabilityTypeViewPermission probabilityType = reportsPermission.getProbabilityTypeViewPermission();
				probabilityTypeVO.setProbabilityTypePermissionId(probabilityType.getProbabilityTypeViewPermissionId());
				probabilityTypeVO.setProbabilityTypePermissionAll(probabilityType.isProbabilityTypeViewPermissionAll());
				if (probabilityType.getReportsCommonPermission() != null) {
					probabilityTypeVO.setReportsCommonPermissionVO(
							setReportsCommonPermission(probabilityType.getReportsCommonPermission()));
				}
				reportsVO.setProbabilityTypePermissionVO(probabilityTypeVO);
			}
			// Region wise view
			if (reportsPermission.getRegionWiseViewPermission() != null) {
				RegionViewPermissionVO regionViewPermissionVO = new RegionViewPermissionVO();
				RegionWiseViewPermission regionWiseView = reportsPermission.getRegionWiseViewPermission();
				regionViewPermissionVO.setRegionViewPermissionId(regionWiseView.getRegionWiseViewPermissionId());
				regionViewPermissionVO.setRegionViewPermissionAll(regionWiseView.isRegionWiseViewPermissionAll());
				if (regionWiseView.getReportsCommonPermission() != null) {
					regionViewPermissionVO.setReportsCommonPermissionVO(
							setReportsCommonPermission(regionWiseView.getReportsCommonPermission()));
				}
				reportsVO.setRegionViewPermissionVO(regionViewPermissionVO);
			}
			// Business unit wise view
			if (reportsPermission.getBusinessUnitWiseViewPermission() != null) {
				BusinessUnitViewPermissionVO businessUnitViewVO = new BusinessUnitViewPermissionVO();
				BusinessUnitWiseViewPermission businessUnitView = reportsPermission.getBusinessUnitWiseViewPermission();
				businessUnitViewVO
						.setBusinessUnitViewPermissionId(businessUnitView.getBusinessUnitWiseViewPermissionId());
				businessUnitViewVO
						.setBusinessUnitViewPermissionAll(businessUnitView.isBusinessUnitWiseViewPermissionAll());
				if (businessUnitView.getReportsCommonPermission() != null) {
					businessUnitViewVO.setReportsCommonPermissionVO(
							setReportsCommonPermission(businessUnitView.getReportsCommonPermission()));
				}
				reportsVO.setBuViewPermissionVO(businessUnitViewVO);
			}
			// client wise view
			if (reportsPermission.getClientWiseViewPermission() != null) {
				ClientWisePermissionVO clientWiseVO = new ClientWisePermissionVO();
				ClientWiseViewPermission clientView = reportsPermission.getClientWiseViewPermission();
				clientWiseVO.setClientWisePermissionId(clientView.getClientWiseViewPermissionId());
				clientWiseVO.setClientWisePermissionAll(clientView.isClientWiseViewPermissionAll());
				if (clientView.getReportsCommonPermission() != null) {
					clientWiseVO.setReportsCommonPermissionVO(
							setReportsCommonPermission(clientView.getReportsCommonPermission()));
				}
				reportsVO.setClientWisePermissionVO(clientWiseVO);
			}
			// Archive
			if (reportsPermission.getArchiveWisePermission() != null) {
				ArchiveWisePermissionVO archiveWiseVO = new ArchiveWisePermissionVO();
				ArchiveWisePermission archiveWise = reportsPermission.getArchiveWisePermission();
				archiveWiseVO.setArchiveWisePermissionId(archiveWise.getArchiveWisePermissionId());
				archiveWiseVO.setArchiveWisePermissionAll(archiveWise.isArchiveWisePermissionAll());
				if (archiveWise.getReportsCommonPermission() != null) {
					archiveWiseVO.setReportsCommonPermissionVO(
							setReportsCommonPermission(archiveWise.getReportsCommonPermission()));
				}
				reportsVO.setArchiveWisePermissionVO(archiveWiseVO);
			}
			rolesVO.setReportsPermissionVO(reportsVO);
		}
		// Settings Permission
		if (roles.getSettingsPermission() != null) {
			SettingsPermissionVO settingsVO = new SettingsPermissionVO();
			SettingsPermission settingsPermission = roles.getSettingsPermission();
			settingsVO.setSettingsPermissionId(settingsPermission.getSettingsPermissionId());
			settingsVO.setSettingsPermissionAll(settingsPermission.isSettingsPermissionAll());
			settingsVO.setViewAll(settingsPermission.isViewAllEntriesRequired());
			// Roles Permission
			if (settingsPermission.getRolesPermission() != null) {
				RolesPermissionVO rolesPermissionVO = new RolesPermissionVO();
				RolesPermission rolesPermission = settingsPermission.getRolesPermission();
				rolesPermissionVO.setRolesPermissionId(rolesPermission.getRolesPermissionId());
				rolesPermissionVO.setRolesPermissionAll(rolesPermission.isRolesPermissionAll());
				rolesPermissionVO.setDeleteRequired(rolesPermission.isDeleteRequired());
				rolesPermissionVO.setCreateRoleRequired(rolesPermission.isCreateRoleRequired());
				rolesPermissionVO.setEditRoleRequired(rolesPermission.isEditRoleRequired());
				rolesPermissionVO.setActive(rolesPermission.isActive());
				settingsVO.setRolesPermissionVO(rolesPermissionVO);
			}
			// Role User Permission
			if (settingsPermission.getRoleUserPermission() != null) {
				RoleUserAssignmentPermissionVO roleUserVO = new RoleUserAssignmentPermissionVO();
				RoleUserPermission roleUserPermission = settingsPermission.getRoleUserPermission();
				roleUserVO.setRoleUserAssignmentPermissionId(roleUserPermission.getRoleUserPermissionId());
				roleUserVO.setRoleUserAssignmentPermissionAll(roleUserPermission.isRoleUserPermissionAll());
				SettingsCommonPermission settingsCommonPermission = roleUserPermission.getSettingsCommonPermission();
				roleUserVO.setCreateOrUploadRequired(settingsCommonPermission.getIsAddRequired());
				roleUserVO.setEditOrReUploadRequired(settingsCommonPermission.getIsEditRequired());
				roleUserVO.setDeleteRequired(settingsCommonPermission.getIsDeleteRequired());
				roleUserVO.setViewRequired(settingsCommonPermission.getIsViewRequired());
				roleUserVO.setActive(settingsCommonPermission.isActive());
				settingsVO.setRoleUserAssignmentPermissionVO(roleUserVO);
			}

			// Explicit permission
			if (settingsPermission.getExplicitPermission() != null) {
				ExplicitPermissionVO explicitPermissionVO = new ExplicitPermissionVO();
				ExplicitPermission explicitPermission = settingsPermission.getExplicitPermission();
				explicitPermissionVO.setExplicitPermissionId(explicitPermission.getExplicitPermissionId());
				explicitPermissionVO.setExplicitPermissionAll(explicitPermission.isExplicitPermissionAll());
				SettingsCommonPermission settingsCommonPermission = explicitPermission.getSettingsCommonPermission();
				explicitPermissionVO.setViewRequired(settingsCommonPermission.getIsViewRequired());
				explicitPermissionVO.setDeleteRequired(settingsCommonPermission.getIsDeleteRequired());
				explicitPermissionVO.setEditRequired(settingsCommonPermission.getIsEditRequired());
				explicitPermissionVO.setCreateRequired(settingsCommonPermission.getIsAddRequired());
				explicitPermissionVO.setActive(settingsCommonPermission.isActive());
				settingsVO.setExplicitPermissionVO(explicitPermissionVO);
			}

			// Annual Target Entry
			if (settingsPermission.getAnnualTargetEntryPermission() != null) {
				AnnualTargetEntryPermissionVO annualTargetEntryPermissionVO = new AnnualTargetEntryPermissionVO();
				AnnualTargetEntryPermission annualTargetEntryPermission = settingsPermission
						.getAnnualTargetEntryPermission();
				annualTargetEntryPermissionVO.setAnnualTargetEntryPermissionId(
						annualTargetEntryPermission.getAnnualTargetEntryPermissionId());
				annualTargetEntryPermissionVO.setAnnualTargetEntryPermissionAll(
						annualTargetEntryPermission.isAnnualTargetEntryPermissionAll());
				annualTargetEntryPermissionVO.setViewRequired(annualTargetEntryPermission.isViewRequired());
				annualTargetEntryPermissionVO.setDeleteRequired(annualTargetEntryPermission.isDeleteRequired());
				annualTargetEntryPermissionVO.setEditRequired(annualTargetEntryPermission.isReuploadOrEditRequired());
				annualTargetEntryPermissionVO.setCreateRequired(annualTargetEntryPermission.isAddOrUploadRequired());
				annualTargetEntryPermissionVO.setActive(annualTargetEntryPermission.isActive());
				settingsVO.setAnnualTargetEntryPermissionVO(annualTargetEntryPermissionVO);
			}
			rolesVO.setSettingsPermissionVO(settingsVO);
		}

		// Calendar
		if (roles.getCalendarPermission() != null) {
			CalendarPermissionVO calendarPermissionVO = new CalendarPermissionVO();
			CalendarPermission calendarPermission = roles.getCalendarPermission();
			calendarPermissionVO.setCalendarPermissionId(calendarPermission.getCalendarPermissionId());
			calendarPermissionVO.setCalendarPermissionAll(calendarPermission.isCalendarPermissionAll());
			calendarPermissionVO.setViewAllCalendarPermission(calendarPermission.isViewAllEntriesRequired());

			// Holiday Calendar
			if (calendarPermission.getHolidayCalendarPermission() != null) {
				HolidayCalendarPermissionVO holidayCalendarPermissionVO = new HolidayCalendarPermissionVO();
				HolidayCalendarPermission holidayCalendarPermission = calendarPermission.getHolidayCalendarPermission();
				holidayCalendarPermissionVO
						.setHolidayCalendarPermissionId(holidayCalendarPermission.getHolidayCalendarPermissionId());
				holidayCalendarPermissionVO
						.setHolidayCalendarPermissionAll(holidayCalendarPermission.isHolidayCalendarPermissionAll());
				if (holidayCalendarPermission.getCalendarCommonPermission() != null) {
					holidayCalendarPermissionVO.setCommonCalendarPermissionVO(
							setCalendarCommonPermission(holidayCalendarPermission.getCalendarCommonPermission()));
				}
				calendarPermissionVO.setHolidayCalendarPermissionVO(holidayCalendarPermissionVO);
			}
			// Fortnightly meetings
			if (calendarPermission.getFortnightlyMeetingsPermission() != null) {
				FortnightlyMeetingPermissionVO fortnightlyMeetingPermissionVO = new FortnightlyMeetingPermissionVO();
				FortnightlyMeetingsPermission fortnightlyMeetingsPermission = calendarPermission
						.getFortnightlyMeetingsPermission();
				fortnightlyMeetingPermissionVO.setFortnightlyMeetingPermissionId(
						fortnightlyMeetingsPermission.getFortnightlyMeetingsPermissionId());
				fortnightlyMeetingPermissionVO.setFortnightlyMeetingPermissionAll(
						fortnightlyMeetingsPermission.isFortnightlyMeetingsPermissionAll());
				if (fortnightlyMeetingsPermission.getCalendarCommonPermission() != null) {
					fortnightlyMeetingPermissionVO.setCommonCalendarPermissionVO(
							setCalendarCommonPermission(fortnightlyMeetingsPermission.getCalendarCommonPermission()));
				}
				calendarPermissionVO.setFortnightlyMeetingPermissionVO(fortnightlyMeetingPermissionVO);
			}
			// BDM Meetings
			if (calendarPermission.getBdmMeetingsPermission() != null) {
				BDMMeetingPermissionVO bdmMeetingPermissionVO = new BDMMeetingPermissionVO();
				BDMMeetingsPermission bdmMeetingsPermission = calendarPermission.getBdmMeetingsPermission();
				bdmMeetingPermissionVO.setBdmMeetingPermissionId(bdmMeetingsPermission.getBdmMeetingsPermissionId());
				bdmMeetingPermissionVO.setBdmMeetingPermissionAll(bdmMeetingsPermission.isBdmMeetingsPermissionAll());
				if (bdmMeetingsPermission.getCalendarCommonPermission() != null) {
					bdmMeetingPermissionVO.setCommonCalendarPermissionVO(
							setCalendarCommonPermission(bdmMeetingsPermission.getCalendarCommonPermission()));
				}
				calendarPermissionVO.setBdmMeetingPermissionVO(bdmMeetingPermissionVO);
			}
			rolesVO.setCalendarPermissionVO(calendarPermissionVO);
		}
		// Administration
		if (roles.getAdministrationPermission() != null) {
			AdministrationPermissionVO administrationPermissionVO = new AdministrationPermissionVO();
			AdministrationPermission administrationPermission = roles.getAdministrationPermission();
			administrationPermissionVO
					.setAdministrationPermissionId(administrationPermission.getAdministrationPermissionId());
			administrationPermissionVO
					.setAdministrationPermissionAll(administrationPermission.isAdministrationPermissionAll());
			administrationPermissionVO
					.setViewAllAdministrationPermission(administrationPermission.isViewAllEntriesRequired());
			// Account
			if (administrationPermission.getAccountPermission() != null) {
				AccountPermissionVO accountPermissionVO = new AccountPermissionVO();
				AccountPermission accountPermission = administrationPermission.getAccountPermission();
				accountPermissionVO.setAccountPermissionId(accountPermission.getAccountPermissionId());
				accountPermissionVO.setAccountPermissionAll(accountPermission.isAccountPermissionAll());
				if (accountPermission.getAdministrationCommonPermission() != null) {
					accountPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(accountPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setAccountPermissionVO(accountPermissionVO);
			}
			// opportunity
			if (administrationPermission.getOpportunityPermission() != null) {
				OpportunityPermissionVO opportunityPermissionVO = new OpportunityPermissionVO();
				OpportunityPermission opportunityPermission = administrationPermission.getOpportunityPermission();
				opportunityPermissionVO.setOpportunityPermissionId(opportunityPermission.getOpportunityPermissionId());
				opportunityPermissionVO.setOpportunityPermissionAll(opportunityPermission.isOpportunityPermissionAll());
				if (opportunityPermission.getAdministrationCommonPermission() != null) {
					opportunityPermissionVO.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
							opportunityPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setOpportunityPermissionVO(opportunityPermissionVO);
			}
			// business unit
			if (administrationPermission.getBusinessUnitPermission() != null) {
				BusinessUnitPermissionVO businessUnitPermissionVO = new BusinessUnitPermissionVO();
				BusinessUnitPermission businessUnitPermission = administrationPermission.getBusinessUnitPermission();
				businessUnitPermissionVO
						.setBusinessUnitPermissionId(businessUnitPermission.getBusinessUnitPermissionId());
				businessUnitPermissionVO
						.setBusinessUnitPermissionAll(businessUnitPermission.isBusinessUnitPermissionAll());
				if (businessUnitPermission.getAdministrationCommonPermission() != null) {
					businessUnitPermissionVO.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
							businessUnitPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setBusinessUnitPermissionVO(businessUnitPermissionVO);
			}
			// Region
			if (administrationPermission.getRegionPermission() != null) {
				RegionPermissionVO regionPermissionVO = new RegionPermissionVO();
				RegionPermission regionPermission = administrationPermission.getRegionPermission();
				regionPermissionVO.setRegionPermissionId(regionPermission.getRegionPermissionId());
				regionPermissionVO.setRegionPermissionAll(regionPermission.isRegionPermissionAll());
				if (regionPermission.getAdministrationCommonPermission() != null) {
					regionPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(regionPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setRegionPermissionVO(regionPermissionVO);
			}
			// sbu
			if (administrationPermission.getSbuPermission() != null) {
				SBUPermissionVO sbuPermissionVO = new SBUPermissionVO();
				SBUPermission sbuPermission = administrationPermission.getSbuPermission();
				sbuPermissionVO.setSbuPermissionId(sbuPermission.getSbuPermissionId());
				sbuPermissionVO.setSbuPermissionAll(sbuPermission.isSbuPermissionAll());
				if (sbuPermission.getAdministrationCommonPermission() != null) {
					sbuPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(sbuPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setSbuPermissionVO(sbuPermissionVO);
			}
			// sbu head
			if (administrationPermission.getSbuHeadPermission() != null) {
				SBUHeadPermissionVO sbuHeadPermissionVO = new SBUHeadPermissionVO();
				SBUHeadPermission sbuHeadPermission = administrationPermission.getSbuHeadPermission();
				sbuHeadPermissionVO.setSbuHeadPermissionId(sbuHeadPermission.getSbuHeadPermissionId());
				sbuHeadPermissionVO.setSbuHeadPermissionAll(sbuHeadPermission.isSbuHeadPermissionAll());
				if (sbuHeadPermission.getAdministrationCommonPermission() != null) {
					sbuHeadPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(sbuHeadPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setSbuHeadPermissionVO(sbuHeadPermissionVO);
			}
			// location
			if (administrationPermission.getLocationPermission() != null) {
				LocationPermissionVO locationPermissionVO = new LocationPermissionVO();
				LocationPermission locationPermission = administrationPermission.getLocationPermission();
				locationPermissionVO.setLocationPermissionId(locationPermission.getLocationPermissionId());
				locationPermissionVO.setLocationPermissionAll(locationPermission.isLocationPermissionAll());
				if (locationPermission.getAdministrationCommonPermission() != null) {
					locationPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(locationPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setLocationPermissionVO(locationPermissionVO);
			}
			// BDM
			if (administrationPermission.getBdmPermission() != null) {
				BDMPermissionVO bdmPermissionVO = new BDMPermissionVO();
				BDMPermission bdmPermission = administrationPermission.getBdmPermission();
				bdmPermissionVO.setBdmPermissionId(bdmPermission.getBdmPermissionId());
				bdmPermissionVO.setBdmPermissionAll(bdmPermission.isBdmPermissionAll());
				if (bdmPermission.getAdministrationCommonPermission() != null) {
					bdmPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(bdmPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setBdmPermissionVO(bdmPermissionVO);
			}
			// Probability type
			if (administrationPermission.getProbabilityTypePermission() != null) {
				ProbabilityPermissionVO probabilityPermissionVO = new ProbabilityPermissionVO();
				ProbabilityTypePermission probabilityTypePermission = administrationPermission
						.getProbabilityTypePermission();
				probabilityPermissionVO
						.setProbabilityPermissionId(probabilityTypePermission.getProbabilityTypePermissionId());
				probabilityPermissionVO
						.setProbabilityPermissionAll(probabilityTypePermission.isProbabilityTypePermissionAll());
				if (probabilityTypePermission.getAdministrationCommonPermission() != null) {
					probabilityPermissionVO.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
							probabilityTypePermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setProbabilityPermissionVO(probabilityPermissionVO);
			}
			// Business Type
			if (administrationPermission.getBusinessTypePermission() != null) {
				BusinessTypePermissionVO businessTypePermissionVO = new BusinessTypePermissionVO();
				BusinessTypePermission businessTypePermission = administrationPermission.getBusinessTypePermission();
				businessTypePermissionVO
						.setBusinessTypePermissionId(businessTypePermission.getBusinessTypePermissionId());
				businessTypePermissionVO
						.setBusinessTypePermissionAll(businessTypePermission.isBusinessTypePermissionAll());
				if (businessTypePermission.getAdministrationCommonPermission() != null) {
					businessTypePermissionVO.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
							businessTypePermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setBusinessTypePermissionVO(businessTypePermissionVO);
			}
			// COC Practise
			if (administrationPermission.getCocPracticePermission() != null) {
				CocPracticePermissionVO cocPracticePermissionVO = new CocPracticePermissionVO();
				COCPracticePermission cocPracticePermission = administrationPermission.getCocPracticePermission();
				cocPracticePermissionVO.setCocPracticePermissionId(cocPracticePermission.getCocPracticePermissionId());
				cocPracticePermissionVO.setCocPracticePermissionAll(cocPracticePermission.isCocPracticePermissionAll());
				if (cocPracticePermission.getAdministrationCommonPermission() != null) {
					cocPracticePermissionVO.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
							cocPracticePermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setCocPracticePermissionVO(cocPracticePermissionVO);
			}
			// Pricing Type
			if (administrationPermission.getPricingTypePermission() != null) {
				PricingTypePermissionVO pricingTypePermissionVO = new PricingTypePermissionVO();
				PricingTypePermission pricingTypePermission = administrationPermission.getPricingTypePermission();
				pricingTypePermissionVO.setPricingTypePermissionId(pricingTypePermission.getPricingTypePermissionId());
				pricingTypePermissionVO.setPricingTypePermissionAll(pricingTypePermission.isPricingTypePermissionAll());
				if (pricingTypePermission.getAdministrationCommonPermission() != null) {
					pricingTypePermissionVO.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
							pricingTypePermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setPricingTypePermissionVO(pricingTypePermissionVO);
			}
			// Work Order
			if (administrationPermission.getWorkOrderPermission() != null) {
				WorkOrderPermissionVO workOrderPermissionVO = new WorkOrderPermissionVO();
				WorkOrderPermission workOrderPermission = administrationPermission.getWorkOrderPermission();
				workOrderPermissionVO.setWorkOrderPermissionId(workOrderPermission.getWorkOrderPermissionId());
				workOrderPermissionVO.setWorkOrderPermissionAll(workOrderPermission.isWorkOrderPermissionAll());
				if (workOrderPermission.getAdministrationCommonPermission() != null) {
					workOrderPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(workOrderPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setWorkOrderPermissionVO(workOrderPermissionVO);
			}
			// Financial Year
			if (administrationPermission.getFinancialYearPermission() != null) {
				FinancialYearPermissionVO financialYearPermissionVO = new FinancialYearPermissionVO();
				FinancialYearPermission financialYearPermission = administrationPermission.getFinancialYearPermission();
				financialYearPermissionVO
						.setFinancialYearPermissionId(financialYearPermission.getFinancialYearPermissionId());
				financialYearPermissionVO
						.setFinancialYearPermissionAll(financialYearPermission.isFinancialYearPermissionAll());
				financialYearPermissionVO.setActive(financialYearPermission.isActive());
				if (financialYearPermission.getAdministrationCommonPermission() != null) {
					financialYearPermissionVO.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
							financialYearPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setFinancialYearPermissionVO(financialYearPermissionVO);
			}
			// Currency
			if (administrationPermission.getCurrencyPermission() != null) {
				CurrencyPermissionVO currencyPermissionVO = new CurrencyPermissionVO();
				CurrencyPermission currencyPermission = administrationPermission.getCurrencyPermission();
				currencyPermissionVO.setCurrencyPermissionId(currencyPermission.getCurrencyPermissionId());
				currencyPermissionVO.setCurrencyPermissionAll(currencyPermission.isCurrencyPermissionAll());
				currencyPermissionVO.setActive(currencyPermission.isActive());
				if (currencyPermission.getAdministrationCommonPermission() != null) {
					currencyPermissionVO.setCommonAdministrationPermissionVO(
							setCommonAdministrationPermission(currencyPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setCurrencyPermissionVO(currencyPermissionVO);
			}
			// Notofication config
			if (administrationPermission.getNotificationConfigurationPermission() != null) {
				NotificationConfigPermissionVO notificationConfigPermissionVO = new NotificationConfigPermissionVO();
				NotificationConfigurationPermission notificationConfigurationPermission = administrationPermission
						.getNotificationConfigurationPermission();
				notificationConfigPermissionVO.setNotificationConfigPermissionId(
						notificationConfigurationPermission.getNotificationConfigurationPermissionId());
				notificationConfigPermissionVO.setNotificationConfigPermissionAll(
						notificationConfigurationPermission.isNotificationConfigurationPermissionAll());
				if (notificationConfigurationPermission.getAdministrationCommonPermission() != null) {
					notificationConfigPermissionVO
							.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
									notificationConfigurationPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setNotificationConfigPermissionVO(notificationConfigPermissionVO);
			}
			// Global Leave Loss Factor
			if (administrationPermission.getGlobalLeaveLossFactorPermission() != null) {
				GlobalLeaveLassFactorPermissionVO globalLeaveLossFactorPermissionVO = new GlobalLeaveLassFactorPermissionVO();
				GlobalLeaveLossFactorPermission globalLeaveLossFactorPermission = administrationPermission
						.getGlobalLeaveLossFactorPermission();
				globalLeaveLossFactorPermissionVO.setGlobalLeaveLassFactorPermissionId(
						globalLeaveLossFactorPermission.getGlobalLeaveLossFactorPermissionId());
				globalLeaveLossFactorPermissionVO.setGlobalLeaveLassFactorPermissionAll(
						globalLeaveLossFactorPermission.isGlobalLeaveLossFactorPermissionAll());
				if (globalLeaveLossFactorPermission.getAdministrationCommonPermission() != null) {
					globalLeaveLossFactorPermissionVO
							.setCommonAdministrationPermissionVO(setCommonAdministrationPermission(
									globalLeaveLossFactorPermission.getAdministrationCommonPermission()));
				}
				administrationPermissionVO.setGlobalLeaveLassFactorPermissionVO(globalLeaveLossFactorPermissionVO);
			}
			rolesVO.setAdministrationPermissionVO(administrationPermissionVO);
		}
		return rolesVO;
	}

	private static ReportsCommonPermissionVO setReportsCommonPermission(
			ReportsCommonPermission reportsCommonPermission) {
		ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
		reportsCommonPermissionVO.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
		reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
		reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
		reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
		reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
		return reportsCommonPermissionVO;
	}

	private static CommonCalendarPermissionVO setCalendarCommonPermission(
			CalendarCommonPermission calendarCommonPermission) {
		CommonCalendarPermissionVO calendarCommonPermissionVO = new CommonCalendarPermissionVO();
		calendarCommonPermissionVO
				.setCommonCalendarPermissionId(calendarCommonPermission.getCalendarCommonPermissionId());
		calendarCommonPermissionVO.setViewRequired(calendarCommonPermission.isViewRequired());
		calendarCommonPermissionVO.setAddRequired(calendarCommonPermission.isAddRequired());
		calendarCommonPermissionVO.setEditRequired(calendarCommonPermission.isEditRequired());
		calendarCommonPermissionVO.setDeleteRequired(calendarCommonPermission.isDeleteRequired());
		return calendarCommonPermissionVO;
	}

	private static CommonAdministrationPermissionVO setCommonAdministrationPermission(
			AdministrationCommonPermission administrationCommonPermission) {
		CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
		commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
				administrationCommonPermission.getAdministrationCommonPermissionId());
		commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
		commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
		commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
		commonAdministrationPermissionVO
				.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
		commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
		return commonAdministrationPermissionVO;
	}

}
