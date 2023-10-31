package com.nous.rollingrevenue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
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
import com.nous.rollingrevenue.repository.AccountPermissionRepository;
import com.nous.rollingrevenue.repository.AdministrationCommonPermissionRepository;
import com.nous.rollingrevenue.repository.AdministrationPermissionRepository;
import com.nous.rollingrevenue.repository.AnnualTargetEntryPermissionRepository;
import com.nous.rollingrevenue.repository.ArchiveWisePermissionRepository;
import com.nous.rollingrevenue.repository.BDMMeetingsPermissionRepository;
import com.nous.rollingrevenue.repository.BDMPermissionRepository;
import com.nous.rollingrevenue.repository.BusinessTypePermissionRepository;
import com.nous.rollingrevenue.repository.BusinessTypeViewPermissionRepository;
import com.nous.rollingrevenue.repository.BusinessUnitPermissionRepository;
import com.nous.rollingrevenue.repository.BusinessUnitWiseViewPermissionRepository;
import com.nous.rollingrevenue.repository.COCPracticePermissionRepository;
import com.nous.rollingrevenue.repository.CalendarCommonPermissionRepository;
import com.nous.rollingrevenue.repository.CalendarPermissionRepository;
import com.nous.rollingrevenue.repository.ClientWiseViewPermissionRepository;
import com.nous.rollingrevenue.repository.CurrencyPermissionRepository;
import com.nous.rollingrevenue.repository.DashboardPermissionRepository;
import com.nous.rollingrevenue.repository.ExplicitPermissionRepository;
import com.nous.rollingrevenue.repository.FinancialYearPermissionRepository;
import com.nous.rollingrevenue.repository.FortnightlyMeetingsPermissionRepository;
import com.nous.rollingrevenue.repository.GlobalLeaveLossFactorPermissionRepository;
import com.nous.rollingrevenue.repository.HolidayCalendarPermissionRepository;
import com.nous.rollingrevenue.repository.InvoiceDataUploadPermissionRepository;
import com.nous.rollingrevenue.repository.LocationPermissionRepository;
import com.nous.rollingrevenue.repository.NotificationConfigurationPermissionRepository;
import com.nous.rollingrevenue.repository.OpportunityPermissionRepository;
import com.nous.rollingrevenue.repository.PricingTypePermissionRepository;
import com.nous.rollingrevenue.repository.ProbabilityTypePermissionRepository;
import com.nous.rollingrevenue.repository.ProbabilityTypeViewPermissionRepository;
import com.nous.rollingrevenue.repository.RegionPermissionRepository;
import com.nous.rollingrevenue.repository.RegionWiseViewPermissionRepository;
import com.nous.rollingrevenue.repository.ReportsCommonPermissionRepository;
import com.nous.rollingrevenue.repository.ReportsPermissionRepository;
import com.nous.rollingrevenue.repository.RevenuePermissionRepository;
import com.nous.rollingrevenue.repository.ReviewandPublishPermissionRepository;
import com.nous.rollingrevenue.repository.RoleUserPermissionRepository;
import com.nous.rollingrevenue.repository.RolesPermissionRepository;
import com.nous.rollingrevenue.repository.RolesRepository;
import com.nous.rollingrevenue.repository.RollingRevenueEntryPermissionRepository;
import com.nous.rollingrevenue.repository.SBUClientViewPermissionRepository;
import com.nous.rollingrevenue.repository.SBUHeadPermissionRepository;
import com.nous.rollingrevenue.repository.SBUPermissionRepository;
import com.nous.rollingrevenue.repository.SettingsCommonPermissionRepository;
import com.nous.rollingrevenue.repository.SettingsPermissionRepository;
import com.nous.rollingrevenue.repository.WorkOrderPermissionRepository;
import com.nous.rollingrevenue.service.RolesService;
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

@Service
@Transactional(readOnly = true)
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private DashboardPermissionRepository dashboardPermissionRepository;

	@Autowired
	private RevenuePermissionRepository revenuePermissionRepository;

	@Autowired
	private RollingRevenueEntryPermissionRepository rollingRevenueEntryPermissionRepository;

	@Autowired
	private InvoiceDataUploadPermissionRepository invoiceDataUploadPermissionRepository;

	@Autowired
	private ReviewandPublishPermissionRepository reviewandPublishPermissionRepository;

	@Autowired
	private ReportsPermissionRepository reportsPermissionRepository;

	@Autowired
	private BusinessTypeViewPermissionRepository businessTypeViewPermissionRepository;

	@Autowired
	private SBUClientViewPermissionRepository sbuClientViewPermissionRepository;

	@Autowired
	private ProbabilityTypeViewPermissionRepository probabilityTypeViewPermissionRepository;

	@Autowired
	private RegionWiseViewPermissionRepository regionWiseViewPermissionRepository;

	@Autowired
	private BusinessUnitWiseViewPermissionRepository businessUnitWiseViewPermissionRepository;

	@Autowired
	private ClientWiseViewPermissionRepository clientWiseViewPermissionRepository;

	@Autowired
	private ArchiveWisePermissionRepository archiveWisePermissionRepository;

	@Autowired
	private ReportsCommonPermissionRepository reportsCommonPermissionRepository;

	@Autowired
	private SettingsPermissionRepository settingsPermissionRepository;

	@Autowired
	private RolesPermissionRepository rolesPermissionRepository;

	@Autowired
	private RoleUserPermissionRepository roleUserPermissionRepository;

	@Autowired
	private ExplicitPermissionRepository explicitPermissionRepository;

	@Autowired
	private SettingsCommonPermissionRepository settingsCommonPermissionRepository;

	@Autowired
	private AnnualTargetEntryPermissionRepository annualTargetEntryPermissionRepository;

	@Autowired
	private CalendarPermissionRepository calendarPermissionRepository;

	@Autowired
	private HolidayCalendarPermissionRepository holidayCalendarPermissionRepository;

	@Autowired
	private FortnightlyMeetingsPermissionRepository fortnightlyMeetingsPermissionRepository;

	@Autowired
	private BDMMeetingsPermissionRepository bdmMeetingsPermissionRepository;

	@Autowired
	private CalendarCommonPermissionRepository calendarCommonPermissionRepository;

	@Autowired
	private AdministrationPermissionRepository administrationPermissionRepository;

	@Autowired
	private AccountPermissionRepository accountPermissionRepository;

	@Autowired
	private OpportunityPermissionRepository opportunityPermissionRepository;

	@Autowired
	private BusinessUnitPermissionRepository businessUnitPermissionRepository;

	@Autowired
	private RegionPermissionRepository regionPermissionRepository;

	@Autowired
	private SBUPermissionRepository sbuPermissionRepository;

	@Autowired
	private SBUHeadPermissionRepository sbuHeadPermissionRepository;

	@Autowired
	private LocationPermissionRepository locationPermissionRepository;

	@Autowired
	private BDMPermissionRepository bdmPermissionRepository;

	@Autowired
	private ProbabilityTypePermissionRepository probabilityTypePermissionRepository;

	@Autowired
	private BusinessTypePermissionRepository businessTypePermissionRepository;

	@Autowired
	private COCPracticePermissionRepository cocPracticePermissionRepository;

	@Autowired
	private PricingTypePermissionRepository pricingTypePermissionRepository;

	@Autowired
	private WorkOrderPermissionRepository workOrderPermissionRepository;

	@Autowired
	private FinancialYearPermissionRepository financialYearPermissionRepository;

	@Autowired
	private CurrencyPermissionRepository currencyPermissionRepository;

	@Autowired
	private NotificationConfigurationPermissionRepository notificationConfigurationPermissionRepository;

	@Autowired
	private GlobalLeaveLossFactorPermissionRepository globalLeaveLossFactorPermissionRepository;

	@Autowired
	private AdministrationCommonPermissionRepository administrationCommonPermissionRepository;

	@Override
	@Transactional
	public void saveRolesDetails(RolesVO rolesVO) {

		if (rolesVO != null) {
			Roles roles = new Roles();
			roles.setRoleName(rolesVO.getRoleName());
			roles.setRoleDisplayName(rolesVO.getRoleDisplayName());
			roles.setSelectAll(rolesVO.isSelectAllPermissions());

			// Dashboard permission
			if (rolesVO.getDashboardPermissionVO() != null) {
				DashboardPermissionVO dashboardPermissionVO = rolesVO.getDashboardPermissionVO();

				DashboardPermission dashboardPermission = new DashboardPermission();
				dashboardPermission.setDashboardPermissionAll(dashboardPermissionVO.isDashboardPermissionAll());
				dashboardPermission.setReadDashboardRequired(dashboardPermissionVO.isReadDashboardRequired());
				dashboardPermission.setEditDashboardRequired(dashboardPermissionVO.isEditDashboardRequired());
				DashboardPermission savedDashboard = dashboardPermissionRepository.save(dashboardPermission);
				roles.setDashboardPermission(savedDashboard);
			}

			// RevenuePermission
			if (rolesVO.getRevenuePermissionVO() != null) {
				RevenuePermissionVO revenuePermissionVO = rolesVO.getRevenuePermissionVO();

				RevenuePermission revenuePermission = new RevenuePermission();
				revenuePermission.setRevenuePermissionAll(revenuePermissionVO.isRevenuePermissionAll());
				revenuePermission.setViewAllEntriesRequired(revenuePermissionVO.isViewAllEntries());

				if (revenuePermissionVO.getRollingRevenueEntryPermissionVO() != null) {
					RollingRevenueEntryPermissionVO revenueEntryPermissionVO = revenuePermissionVO
							.getRollingRevenueEntryPermissionVO();

					RollingRevenueEntryPermission revenueEntryPermission = new RollingRevenueEntryPermission();
					revenueEntryPermission.setRollingRevenueEntryPermissionAll(
							revenueEntryPermissionVO.isRollingrevenueEntryPermissionAll());
					revenueEntryPermission
							.setViewAllEntriesRequired(revenueEntryPermissionVO.isViewAllEntriesRequired());
					revenueEntryPermission
							.setAddRevenueEntryRequired(revenueEntryPermissionVO.isAddRevenueEntryRequired());
					revenueEntryPermission
							.setEditRevenueEntryRequired(revenueEntryPermissionVO.isEditRevenueEntryRequired());
					revenueEntryPermission
							.setDeleteRevenueEntryRequired(revenueEntryPermissionVO.isDeleteRevenueEntryRequired());
					revenueEntryPermission
							.setCopyRevenueEntryRequired(revenueEntryPermissionVO.isCopyRevenueEntryRequired());
					revenueEntryPermission
							.setSubmitRevenueEntryRequired(revenueEntryPermissionVO.isSubmitRevenueEntryRequired());
					revenueEntryPermission.setExportRequired(revenueEntryPermissionVO.isExportRequired());
					RollingRevenueEntryPermission savedRevenueEntryPermission = rollingRevenueEntryPermissionRepository
							.save(revenueEntryPermission);
					revenuePermission.setRollingRevenueEntryPermission(savedRevenueEntryPermission);
				}
				if (revenuePermissionVO.getInvoiceDataUploadPermissionVO() != null) {
					InvoiceDataUploadPermissionVO invoiceUploadPermissionVO = revenuePermissionVO
							.getInvoiceDataUploadPermissionVO();

					InvoiceDataUploadPermission dataUploadPermission = new InvoiceDataUploadPermission();
					dataUploadPermission.setInvoiceDataUploadPermissionAll(
							invoiceUploadPermissionVO.isInvoiceDataUploadPermissionAll());
					dataUploadPermission.setViewListRequired(invoiceUploadPermissionVO.isViewListRequired());
					dataUploadPermission.setUploadDataRequired(invoiceUploadPermissionVO.isUploadDataRequired());
					dataUploadPermission.setEditOrReupload(invoiceUploadPermissionVO.isEditableRequired());
					dataUploadPermission.setDeleteDataRequired(invoiceUploadPermissionVO.isDeleteRequired());
					InvoiceDataUploadPermission savedInvoiceData = invoiceDataUploadPermissionRepository
							.save(dataUploadPermission);
					revenuePermission.setInvoiceDataUploadPermission(savedInvoiceData);
				}
				if (revenuePermissionVO.getReviewandPublishPermissionVO() != null) {
					ReviewandPublishPermissionVO reviewandPublishPermissionVO = revenuePermissionVO
							.getReviewandPublishPermissionVO();

					ReviewandPublishPermission publishPermission = new ReviewandPublishPermission();
					publishPermission.setReviewAndPublishPermissionAll(
							reviewandPublishPermissionVO.isReviewandPublishPermissionAll());
					publishPermission.setViewRequired(reviewandPublishPermissionVO.isViewRequired());
					publishPermission
							.setReviewAndSubmitRequired(reviewandPublishPermissionVO.isReviewAndSubmitRequired());
					publishPermission.setSaveRequired(reviewandPublishPermissionVO.isSaveRequired());
					publishPermission.setCopyRequired(reviewandPublishPermissionVO.isCopyRequired());
					publishPermission.setEditRequired(reviewandPublishPermissionVO.isEditRequired());
					publishPermission.setPublishRequired(reviewandPublishPermissionVO.isPublishRequired());
					publishPermission
							.setSaveRecipientsRequired(reviewandPublishPermissionVO.isSaveRecipientsRequired());
					ReviewandPublishPermission savedPublishPermission = reviewandPublishPermissionRepository
							.save(publishPermission);
					revenuePermission.setReviewandPublishPermission(savedPublishPermission);
				}
				RevenuePermission saveRevenuePermission = revenuePermissionRepository.save(revenuePermission);
				roles.setRevenuePermission(saveRevenuePermission);
			}

			// ReportsPermission
			if (rolesVO.getReportsPermissionVO() != null) {
				ReportsPermissionVO reportsPermissionVO = rolesVO.getReportsPermissionVO();

				ReportsPermission reportsPermission = new ReportsPermission();
				reportsPermission.setReportsPermissionAll(reportsPermissionVO.isReportsPermissionsAll());
				reportsPermission.setViewAllEntriesRequired(reportsPermissionVO.isViewAllEntries());
				if (reportsPermissionVO.getBusinessTypeViewPermissionVO() != null) {
					BusinessTypeViewPermissionVO businessTypeViewPermissionVO = reportsPermissionVO
							.getBusinessTypeViewPermissionVO();

					BusinessTypeViewPermission businessTypeViewPermission = new BusinessTypeViewPermission();
					businessTypeViewPermission.setBusinessTypeViewPermissionAll(
							businessTypeViewPermissionVO.isBusinessTypeViewPermissionAll());
					if (businessTypeViewPermissionVO.getReportsCommonPermissionVO() != null) {
						ReportsCommonPermissionVO reportsCommonPermissionVO = businessTypeViewPermissionVO
								.getReportsCommonPermissionVO();
						businessTypeViewPermission.setReportsCommonPermission(
								saveReportsCommonPermissionDetails(reportsCommonPermissionVO));
					}
					BusinessTypeViewPermission savedBusinessTypeView = businessTypeViewPermissionRepository
							.save(businessTypeViewPermission);
					reportsPermission.setBusinessTypeViewPermission(savedBusinessTypeView);
				}
				if (reportsPermissionVO.getSbuClientViewPermissionVO() != null) {
					SBUClientViewPermissionVO sbuClientViewPermissionVO = reportsPermissionVO
							.getSbuClientViewPermissionVO();

					SBUClientViewPermission sbuClientViewPermission = new SBUClientViewPermission();
					sbuClientViewPermission
							.setSbuClientViewPermissionAll(sbuClientViewPermissionVO.isSbuClientViewPermissionAll());
					if (sbuClientViewPermissionVO.getReportsCommonPermissionVO() != null) {
						ReportsCommonPermissionVO reportsCommonPermissionVO = sbuClientViewPermissionVO
								.getReportsCommonPermissionVO();
						sbuClientViewPermission.setReportsCommonPermission(
								saveReportsCommonPermissionDetails(reportsCommonPermissionVO));
					}
					SBUClientViewPermission savedSBUClientViewPermission = sbuClientViewPermissionRepository
							.save(sbuClientViewPermission);
					reportsPermission.setSbuClientViewPermission(savedSBUClientViewPermission);
				}
				if (reportsPermissionVO.getProbabilityTypePermissionVO() != null) {
					ProbabilityTypePermissionVO probabilityTypePermissionVO = reportsPermissionVO
							.getProbabilityTypePermissionVO();

					ProbabilityTypeViewPermission probabilityTypeViewPermission = new ProbabilityTypeViewPermission();
					probabilityTypeViewPermission.setProbabilityTypeViewPermissionAll(
							probabilityTypePermissionVO.isProbabilityTypePermissionAll());
					if (probabilityTypePermissionVO.getReportsCommonPermissionVO() != null) {
						ReportsCommonPermissionVO reportsCommonPermissionVO = probabilityTypePermissionVO
								.getReportsCommonPermissionVO();
						probabilityTypeViewPermission.setReportsCommonPermission(
								saveReportsCommonPermissionDetails(reportsCommonPermissionVO));
					}
					ProbabilityTypeViewPermission savedProbabilityTypeView = probabilityTypeViewPermissionRepository
							.save(probabilityTypeViewPermission);
					reportsPermission.setProbabilityTypeViewPermission(savedProbabilityTypeView);
				}
				if (reportsPermissionVO.getRegionViewPermissionVO() != null) {
					RegionViewPermissionVO regionViewPermissionVO = reportsPermissionVO.getRegionViewPermissionVO();

					RegionWiseViewPermission regionWiseViewPermission = new RegionWiseViewPermission();
					regionWiseViewPermission
							.setRegionWiseViewPermissionAll(regionViewPermissionVO.isRegionViewPermissionAll());
					if (regionViewPermissionVO.getReportsCommonPermissionVO() != null) {
						ReportsCommonPermissionVO reportsCommonPermissionVO = regionViewPermissionVO
								.getReportsCommonPermissionVO();
						regionWiseViewPermission.setReportsCommonPermission(
								saveReportsCommonPermissionDetails(reportsCommonPermissionVO));
					}
					RegionWiseViewPermission savedRegionWiseView = regionWiseViewPermissionRepository
							.save(regionWiseViewPermission);
					reportsPermission.setRegionWiseViewPermission(savedRegionWiseView);
				}
				if (reportsPermissionVO.getBuViewPermissionVO() != null) {
					BusinessUnitViewPermissionVO buViewPermissionVO = reportsPermissionVO.getBuViewPermissionVO();

					BusinessUnitWiseViewPermission businessUnitWiseViewPermission = new BusinessUnitWiseViewPermission();
					businessUnitWiseViewPermission
							.setBusinessUnitWiseViewPermissionAll(buViewPermissionVO.isBusinessUnitViewPermissionAll());
					if (buViewPermissionVO.getReportsCommonPermissionVO() != null) {
						ReportsCommonPermissionVO reportsCommonPermissionVO = buViewPermissionVO
								.getReportsCommonPermissionVO();
						businessUnitWiseViewPermission.setReportsCommonPermission(
								saveReportsCommonPermissionDetails(reportsCommonPermissionVO));
					}
					BusinessUnitWiseViewPermission savedBUViewPermission = businessUnitWiseViewPermissionRepository
							.save(businessUnitWiseViewPermission);
					reportsPermission.setBusinessUnitWiseViewPermission(savedBUViewPermission);
				}
				if (reportsPermissionVO.getClientWisePermissionVO() != null) {
					ClientWisePermissionVO clientWisePermissionVO = reportsPermissionVO.getClientWisePermissionVO();

					ClientWiseViewPermission clientWiseViewPermission = new ClientWiseViewPermission();
					clientWiseViewPermission
							.setClientWiseViewPermissionAll(clientWisePermissionVO.isClientWisePermissionAll());
					if (clientWisePermissionVO.getReportsCommonPermissionVO() != null) {
						ReportsCommonPermissionVO reportsCommonPermissionVO = clientWisePermissionVO
								.getReportsCommonPermissionVO();
						clientWiseViewPermission.setReportsCommonPermission(
								saveReportsCommonPermissionDetails(reportsCommonPermissionVO));
					}
					ClientWiseViewPermission savedClientWiseView = clientWiseViewPermissionRepository
							.save(clientWiseViewPermission);
					reportsPermission.setClientWiseViewPermission(savedClientWiseView);
				}
				if (reportsPermissionVO.getArchiveWisePermissionVO() != null) {
					ArchiveWisePermissionVO archiveWisePermissionVO = reportsPermissionVO.getArchiveWisePermissionVO();

					ArchiveWisePermission archiveWisePermission = new ArchiveWisePermission();
					archiveWisePermission
							.setArchiveWisePermissionAll(archiveWisePermissionVO.isArchiveWisePermissionAll());
					if (archiveWisePermissionVO.getReportsCommonPermissionVO() != null) {
						ReportsCommonPermissionVO reportsCommonPermissionVO = archiveWisePermissionVO
								.getReportsCommonPermissionVO();
						archiveWisePermission.setReportsCommonPermission(
								saveReportsCommonPermissionDetails(reportsCommonPermissionVO));
					}
					ArchiveWisePermission savedArchiveWise = archiveWisePermissionRepository
							.save(archiveWisePermission);
					reportsPermission.setArchiveWisePermission(savedArchiveWise);
				}
				ReportsPermission savedReportsPermission = reportsPermissionRepository.save(reportsPermission);
				roles.setReportsPermission(savedReportsPermission);
			}

			// Settings Permission
			if (rolesVO.getSettingsPermissionVO() != null) {
				SettingsPermissionVO settingsPermissionVO = rolesVO.getSettingsPermissionVO();

				SettingsPermission settingsPermission = new SettingsPermission();
				settingsPermission.setSettingsPermissionAll(settingsPermissionVO.isSettingsPermissionAll());
				settingsPermission.setViewAllEntriesRequired(settingsPermissionVO.isViewAll());
				if (settingsPermissionVO.getRolesPermissionVO() != null) {
					RolesPermissionVO rolesPermissionVO = settingsPermissionVO.getRolesPermissionVO();

					RolesPermission rolesPermission = new RolesPermission();
					rolesPermission.setRolesPermissionAll(rolesPermissionVO.isRolesPermissionAll());
					rolesPermission.setDeleteRequired(rolesPermissionVO.isDeleteRequired());
					rolesPermission.setCreateRoleRequired(rolesPermissionVO.isCreateRoleRequired());
					rolesPermission.setEditRoleRequired(rolesPermissionVO.isEditRoleRequired());
					RolesPermission savedRolesPermission = rolesPermissionRepository.save(rolesPermission);
					settingsPermission.setRolesPermission(savedRolesPermission);
				}
				if (settingsPermissionVO.getRoleUserAssignmentPermissionVO() != null) {
					RoleUserAssignmentPermissionVO userAssignmentPermissionVO = settingsPermissionVO
							.getRoleUserAssignmentPermissionVO();

					RoleUserPermission roleUserPermission = new RoleUserPermission();
					roleUserPermission
							.setRoleUserPermissionAll(userAssignmentPermissionVO.isRoleUserAssignmentPermissionAll());

					SettingsCommonPermission settingsCommonPermission = new SettingsCommonPermission();
					settingsCommonPermission.setIsViewRequired(userAssignmentPermissionVO.isViewRequired());
					settingsCommonPermission.setIsAddRequired(userAssignmentPermissionVO.isCreateOrUploadRequired());
					settingsCommonPermission.setIsEditRequired(userAssignmentPermissionVO.isEditOrReUploadRequired());
					settingsCommonPermission.setIsDeleteRequired(userAssignmentPermissionVO.isDeleteRequired());
					SettingsCommonPermission savedSettingsCommonPermission = settingsCommonPermissionRepository
							.save(settingsCommonPermission);
					roleUserPermission.setSettingsCommonPermission(savedSettingsCommonPermission);
					RoleUserPermission savedRoleUserPermission = roleUserPermissionRepository.save(roleUserPermission);
					settingsPermission.setRoleUserPermission(savedRoleUserPermission);
				}
				if (settingsPermissionVO.getExplicitPermissionVO() != null) {
					ExplicitPermissionVO explicitPermissionVO = settingsPermissionVO.getExplicitPermissionVO();

					ExplicitPermission explicitPermission = new ExplicitPermission();
					explicitPermission.setExplicitPermissionAll(explicitPermissionVO.isExplicitPermissionAll());

					SettingsCommonPermission settingsCommonPermission = new SettingsCommonPermission();
					settingsCommonPermission.setIsViewRequired(explicitPermissionVO.isViewRequired());
					settingsCommonPermission.setIsAddRequired(explicitPermissionVO.isCreateRequired());
					settingsCommonPermission.setIsEditRequired(explicitPermissionVO.isEditRequired());
					settingsCommonPermission.setIsDeleteRequired(explicitPermissionVO.isDeleteRequired());
					SettingsCommonPermission savedSettingsCommonPermission = settingsCommonPermissionRepository
							.save(settingsCommonPermission);
					explicitPermission.setSettingsCommonPermission(savedSettingsCommonPermission);
					ExplicitPermission savedExplicitPermission = explicitPermissionRepository.save(explicitPermission);
					settingsPermission.setExplicitPermission(savedExplicitPermission);
				}
				if (settingsPermissionVO.getAnnualTargetEntryPermissionVO() != null) {
					AnnualTargetEntryPermissionVO targetEntryPermissionVO = settingsPermissionVO
							.getAnnualTargetEntryPermissionVO();

					AnnualTargetEntryPermission targetEntryPermission = new AnnualTargetEntryPermission();
					targetEntryPermission.setAnnualTargetEntryPermissionAll(
							targetEntryPermissionVO.isAnnualTargetEntryPermissionAll());
					targetEntryPermission.setViewRequired(targetEntryPermissionVO.isViewRequired());
					targetEntryPermission.setAddOrUploadRequired(targetEntryPermissionVO.isCreateRequired());
					targetEntryPermission.setReuploadOrEditRequired(targetEntryPermissionVO.isEditRequired());
					targetEntryPermission.setDeleteRequired(targetEntryPermissionVO.isDeleteRequired());
					AnnualTargetEntryPermission savedAnnualTargetEntryPermission = annualTargetEntryPermissionRepository
							.save(targetEntryPermission);
					settingsPermission.setAnnualTargetEntryPermission(savedAnnualTargetEntryPermission);
				}
				SettingsPermission savedSettingsPermission = settingsPermissionRepository.save(settingsPermission);
				roles.setSettingsPermission(savedSettingsPermission);
			}

			// Calendar Permissions
			if (rolesVO.getCalendarPermissionVO() != null) {
				CalendarPermissionVO calendarPermissionVO = rolesVO.getCalendarPermissionVO();

				CalendarPermission calendarPermission = new CalendarPermission();
				calendarPermission.setCalendarPermissionAll(calendarPermissionVO.isCalendarPermissionAll());
				calendarPermission.setViewAllEntriesRequired(calendarPermissionVO.isViewAllCalendarPermission());
				if (calendarPermissionVO.getHolidayCalendarPermissionVO() != null) {
					HolidayCalendarPermissionVO holidayCalendarPermissionVO = calendarPermissionVO
							.getHolidayCalendarPermissionVO();

					HolidayCalendarPermission holidayCalendarPermission = new HolidayCalendarPermission();
					holidayCalendarPermission.setHolidayCalendarPermissionAll(
							holidayCalendarPermissionVO.isHolidayCalendarPermissionAll());
					if (holidayCalendarPermissionVO.getCommonCalendarPermissionVO() != null) {
						CommonCalendarPermissionVO commonCalendarPermissionVO = holidayCalendarPermissionVO
								.getCommonCalendarPermissionVO();
						holidayCalendarPermission
								.setCalendarCommonPermission(savedCommonCalendarPermission(commonCalendarPermissionVO));
					}
					HolidayCalendarPermission savedHolidayCalendar = holidayCalendarPermissionRepository
							.save(holidayCalendarPermission);
					calendarPermission.setHolidayCalendarPermission(savedHolidayCalendar);
				}
				if (calendarPermissionVO.getFortnightlyMeetingPermissionVO() != null) {
					FortnightlyMeetingPermissionVO fortnightlyMeetingPermissionVO = calendarPermissionVO
							.getFortnightlyMeetingPermissionVO();

					FortnightlyMeetingsPermission meetingsPermission = new FortnightlyMeetingsPermission();
					meetingsPermission.setFortnightlyMeetingsPermissionAll(
							fortnightlyMeetingPermissionVO.isFortnightlyMeetingPermissionAll());

					if (fortnightlyMeetingPermissionVO.getCommonCalendarPermissionVO() != null) {
						CommonCalendarPermissionVO commonCalendarPermissionVO = fortnightlyMeetingPermissionVO
								.getCommonCalendarPermissionVO();
						meetingsPermission
								.setCalendarCommonPermission(savedCommonCalendarPermission(commonCalendarPermissionVO));
					}
					FortnightlyMeetingsPermission savedFortnightlyMeetings = fortnightlyMeetingsPermissionRepository
							.save(meetingsPermission);
					calendarPermission.setFortnightlyMeetingsPermission(savedFortnightlyMeetings);
				}
				if (calendarPermissionVO.getBdmMeetingPermissionVO() != null) {
					BDMMeetingPermissionVO bdmMeetingPermissionVO = calendarPermissionVO.getBdmMeetingPermissionVO();

					BDMMeetingsPermission bdmMeetingsPermission = new BDMMeetingsPermission();
					bdmMeetingsPermission
							.setBdmMeetingsPermissionAll(bdmMeetingPermissionVO.isBdmMeetingPermissionAll());
					if (bdmMeetingPermissionVO.getCommonCalendarPermissionVO() != null) {
						CommonCalendarPermissionVO commonCalendarPermissionVO = bdmMeetingPermissionVO
								.getCommonCalendarPermissionVO();
						bdmMeetingsPermission
								.setCalendarCommonPermission(savedCommonCalendarPermission(commonCalendarPermissionVO));
					}
					BDMMeetingsPermission savedBDMMeetingsPermission = bdmMeetingsPermissionRepository
							.save(bdmMeetingsPermission);
					calendarPermission.setBdmMeetingsPermission(savedBDMMeetingsPermission);
				}
				CalendarPermission savedCalendarPermission = calendarPermissionRepository.save(calendarPermission);
				roles.setCalendarPermission(savedCalendarPermission);
			}

			// Administration Permissions
			if (rolesVO.getAdministrationPermissionVO() != null) {
				AdministrationPermissionVO administrationPermissionVO = rolesVO.getAdministrationPermissionVO();

				AdministrationPermission administrationPermission = new AdministrationPermission();
				administrationPermission
						.setAdministrationPermissionAll(administrationPermissionVO.isAdministrationPermissionAll());
				administrationPermission
						.setViewAllEntriesRequired(administrationPermissionVO.isViewAllAdministrationPermission());
				if (administrationPermissionVO.getAccountPermissionVO() != null) {
					AccountPermissionVO accountPermissionVO = administrationPermissionVO.getAccountPermissionVO();

					AccountPermission accountPermission = new AccountPermission();
					accountPermission.setAccountPermissionAll(accountPermissionVO.isAccountPermissionAll());
					if (accountPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = accountPermissionVO
								.getCommonAdministrationPermissionVO();
						accountPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					AccountPermission savedAccountPermission = accountPermissionRepository.save(accountPermission);
					administrationPermission.setAccountPermission(savedAccountPermission);
				}
				if (administrationPermissionVO.getOpportunityPermissionVO() != null) {
					OpportunityPermissionVO opportunityPermissionVO = administrationPermissionVO
							.getOpportunityPermissionVO();

					OpportunityPermission opportunityPermission = new OpportunityPermission();
					opportunityPermission
							.setOpportunityPermissionAll(opportunityPermissionVO.isOpportunityPermissionAll());
					if (opportunityPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = opportunityPermissionVO
								.getCommonAdministrationPermissionVO();
						opportunityPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					OpportunityPermission savedOpportunityPermission = opportunityPermissionRepository
							.save(opportunityPermission);
					administrationPermission.setOpportunityPermission(savedOpportunityPermission);
				}
				if (administrationPermissionVO.getBusinessUnitPermissionVO() != null) {
					BusinessUnitPermissionVO businessUnitPermissionVO = administrationPermissionVO
							.getBusinessUnitPermissionVO();

					BusinessUnitPermission businessUnitPermission = new BusinessUnitPermission();
					businessUnitPermission
							.setBusinessUnitPermissionAll(businessUnitPermissionVO.isBusinessUnitPermissionAll());
					if (businessUnitPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = businessUnitPermissionVO
								.getCommonAdministrationPermissionVO();
						businessUnitPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					BusinessUnitPermission savedBusinessUnitPermission = businessUnitPermissionRepository
							.save(businessUnitPermission);
					administrationPermission.setBusinessUnitPermission(savedBusinessUnitPermission);
				}
				if (administrationPermissionVO.getRegionPermissionVO() != null) {
					RegionPermissionVO regionPermissionVO = administrationPermissionVO.getRegionPermissionVO();

					RegionPermission regionPermission = new RegionPermission();
					regionPermission.setRegionPermissionAll(regionPermissionVO.isRegionPermissionAll());
					if (regionPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = regionPermissionVO
								.getCommonAdministrationPermissionVO();
						regionPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					RegionPermission savedRegionPermission = regionPermissionRepository.save(regionPermission);
					administrationPermission.setRegionPermission(savedRegionPermission);
				}
				if (administrationPermissionVO.getSbuPermissionVO() != null) {
					SBUPermissionVO sbuPermissionVO = administrationPermissionVO.getSbuPermissionVO();

					SBUPermission sbuPermission = new SBUPermission();
					sbuPermission.setSbuPermissionAll(sbuPermissionVO.isSbuPermissionAll());
					if (sbuPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = sbuPermissionVO
								.getCommonAdministrationPermissionVO();
						sbuPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					SBUPermission savedSBUPermission = sbuPermissionRepository.save(sbuPermission);
					administrationPermission.setSbuPermission(savedSBUPermission);
				}
				if (administrationPermissionVO.getSbuHeadPermissionVO() != null) {
					SBUHeadPermissionVO sbuHeadPermissionVO = administrationPermissionVO.getSbuHeadPermissionVO();

					SBUHeadPermission sbuHeadPermission = new SBUHeadPermission();
					sbuHeadPermission.setSbuHeadPermissionAll(sbuHeadPermissionVO.isSbuHeadPermissionAll());
					if (sbuHeadPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = sbuHeadPermissionVO
								.getCommonAdministrationPermissionVO();
						sbuHeadPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					SBUHeadPermission saveSBUHeadPermission = sbuHeadPermissionRepository.save(sbuHeadPermission);
					administrationPermission.setSbuHeadPermission(saveSBUHeadPermission);
				}
				if (administrationPermissionVO.getLocationPermissionVO() != null) {
					LocationPermissionVO locationPermissionVO = administrationPermissionVO.getLocationPermissionVO();

					LocationPermission locationPermission = new LocationPermission();
					locationPermission.setLocationPermissionAll(locationPermissionVO.isLocationPermissionAll());
					if (locationPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = locationPermissionVO
								.getCommonAdministrationPermissionVO();
						locationPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					LocationPermission savedLocationPermission = locationPermissionRepository.save(locationPermission);
					administrationPermission.setLocationPermission(savedLocationPermission);
				}
				if (administrationPermissionVO.getBdmPermissionVO() != null) {
					BDMPermissionVO bdmPermissionVO = administrationPermissionVO.getBdmPermissionVO();

					BDMPermission bdmPermission = new BDMPermission();
					bdmPermission.setBdmPermissionAll(bdmPermissionVO.isBdmPermissionAll());
					if (bdmPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = bdmPermissionVO
								.getCommonAdministrationPermissionVO();
						bdmPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					BDMPermission savedBDMPermission = bdmPermissionRepository.save(bdmPermission);
					administrationPermission.setBdmPermission(savedBDMPermission);
				}
				if (administrationPermissionVO.getProbabilityPermissionVO() != null) {
					ProbabilityPermissionVO probabilityPermissionVO = administrationPermissionVO
							.getProbabilityPermissionVO();

					ProbabilityTypePermission probabilityTypePermission = new ProbabilityTypePermission();
					probabilityTypePermission
							.setProbabilityTypePermissionAll(probabilityPermissionVO.isProbabilityPermissionAll());
					if (probabilityPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = probabilityPermissionVO
								.getCommonAdministrationPermissionVO();
						probabilityTypePermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					ProbabilityTypePermission savedProbabilityTypePermission = probabilityTypePermissionRepository
							.save(probabilityTypePermission);
					administrationPermission.setProbabilityTypePermission(savedProbabilityTypePermission);
				}
				if (administrationPermissionVO.getBusinessTypePermissionVO() != null) {
					BusinessTypePermissionVO businessTypePermissionVO = administrationPermissionVO
							.getBusinessTypePermissionVO();

					BusinessTypePermission businessTypePermission = new BusinessTypePermission();
					businessTypePermission
							.setBusinessTypePermissionAll(businessTypePermissionVO.isBusinessTypePermissionAll());
					if (businessTypePermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = businessTypePermissionVO
								.getCommonAdministrationPermissionVO();
						businessTypePermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					BusinessTypePermission savedBusinessTypePermission = businessTypePermissionRepository
							.save(businessTypePermission);
					administrationPermission.setBusinessTypePermission(savedBusinessTypePermission);
				}
				if (administrationPermissionVO.getCocPracticePermissionVO() != null) {
					CocPracticePermissionVO cocPracticePermissionVO = administrationPermissionVO
							.getCocPracticePermissionVO();

					COCPracticePermission cocPracticePermission = new COCPracticePermission();
					cocPracticePermission
							.setCocPracticePermissionAll(cocPracticePermissionVO.isCocPracticePermissionAll());
					if (cocPracticePermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = cocPracticePermissionVO
								.getCommonAdministrationPermissionVO();
						cocPracticePermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					COCPracticePermission savedCOCPracticePermission = cocPracticePermissionRepository
							.save(cocPracticePermission);
					administrationPermission.setCocPracticePermission(savedCOCPracticePermission);
				}
				if (administrationPermissionVO.getPricingTypePermissionVO() != null) {
					PricingTypePermissionVO pricingTypePermissionVO = administrationPermissionVO
							.getPricingTypePermissionVO();

					PricingTypePermission pricingTypePermission = new PricingTypePermission();
					pricingTypePermission
							.setPricingTypePermissionAll(pricingTypePermissionVO.isPricingTypePermissionAll());
					if (pricingTypePermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = pricingTypePermissionVO
								.getCommonAdministrationPermissionVO();
						pricingTypePermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					PricingTypePermission savedPricingTypePermission = pricingTypePermissionRepository
							.save(pricingTypePermission);
					administrationPermission.setPricingTypePermission(savedPricingTypePermission);
				}
				if (administrationPermissionVO.getWorkOrderPermissionVO() != null) {
					WorkOrderPermissionVO workOrderPermissionVO = administrationPermissionVO.getWorkOrderPermissionVO();

					WorkOrderPermission workOrderPermission = new WorkOrderPermission();
					workOrderPermission.setWorkOrderPermissionAll(workOrderPermissionVO.isWorkOrderPermissionAll());
					if (workOrderPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = workOrderPermissionVO
								.getCommonAdministrationPermissionVO();
						workOrderPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					WorkOrderPermission savedWorkOrderPermission = workOrderPermissionRepository
							.save(workOrderPermission);
					administrationPermission.setWorkOrderPermission(savedWorkOrderPermission);
				}
				if (administrationPermissionVO.getFinancialYearPermissionVO() != null) {
					FinancialYearPermissionVO financialYearPermissionVO = administrationPermissionVO
							.getFinancialYearPermissionVO();

					FinancialYearPermission financialYearPermission = new FinancialYearPermission();
					financialYearPermission
							.setFinancialYearPermissionAll(financialYearPermissionVO.isFinancialYearPermissionAll());
					if (financialYearPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = financialYearPermissionVO
								.getCommonAdministrationPermissionVO();
						financialYearPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					FinancialYearPermission savedFinancialYearPermission = financialYearPermissionRepository
							.save(financialYearPermission);
					administrationPermission.setFinancialYearPermission(savedFinancialYearPermission);
				}
				if (administrationPermissionVO.getCurrencyPermissionVO() != null) {
					CurrencyPermissionVO currencyPermissionVO = administrationPermissionVO.getCurrencyPermissionVO();

					CurrencyPermission currencyPermission = new CurrencyPermission();
					currencyPermission.setCurrencyPermissionAll(currencyPermissionVO.isCurrencyPermissionAll());
					if (currencyPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = currencyPermissionVO
								.getCommonAdministrationPermissionVO();
						currencyPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					CurrencyPermission savedCurrencyPermission = currencyPermissionRepository.save(currencyPermission);
					administrationPermission.setCurrencyPermission(savedCurrencyPermission);
				}
				if (administrationPermissionVO.getNotificationConfigPermissionVO() != null) {
					NotificationConfigPermissionVO notificationConfigPermissionVO = administrationPermissionVO
							.getNotificationConfigPermissionVO();

					NotificationConfigurationPermission configurationPermission = new NotificationConfigurationPermission();
					configurationPermission.setNotificationConfigurationPermissionAll(
							notificationConfigPermissionVO.isNotificationConfigPermissionAll());
					if (notificationConfigPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = notificationConfigPermissionVO
								.getCommonAdministrationPermissionVO();
						configurationPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					NotificationConfigurationPermission savedConfigurationPermission = notificationConfigurationPermissionRepository
							.save(configurationPermission);
					administrationPermission.setNotificationConfigurationPermission(savedConfigurationPermission);
				}
				if (administrationPermissionVO.getGlobalLeaveLassFactorPermissionVO() != null) {
					GlobalLeaveLassFactorPermissionVO globalLeaveLassFactorPermissionVO = administrationPermissionVO
							.getGlobalLeaveLassFactorPermissionVO();

					GlobalLeaveLossFactorPermission leaveLossFactorPermission = new GlobalLeaveLossFactorPermission();
					leaveLossFactorPermission.setGlobalLeaveLossFactorPermissionAll(
							globalLeaveLassFactorPermissionVO.isGlobalLeaveLassFactorPermissionAll());
					if (globalLeaveLassFactorPermissionVO.getCommonAdministrationPermissionVO() != null) {
						CommonAdministrationPermissionVO commonAdministrationPermissionVO = globalLeaveLassFactorPermissionVO
								.getCommonAdministrationPermissionVO();
						leaveLossFactorPermission.setAdministrationCommonPermission(
								savedAdministrationCommonPermission(commonAdministrationPermissionVO));
					}
					GlobalLeaveLossFactorPermission savedLeaveLossFactorPermission = globalLeaveLossFactorPermissionRepository
							.save(leaveLossFactorPermission);
					administrationPermission.setGlobalLeaveLossFactorPermission(savedLeaveLossFactorPermission);
				}
				AdministrationPermission savedAdministrationPermission = administrationPermissionRepository
						.save(administrationPermission);
				roles.setAdministrationPermission(savedAdministrationPermission);
			}
			rolesRepository.save(roles);
		}

	}

	private ReportsCommonPermission saveReportsCommonPermissionDetails(
			ReportsCommonPermissionVO reportsCommonPermissionVO) {
		ReportsCommonPermission reportsCommonPermission = new ReportsCommonPermission();
		reportsCommonPermission.setViewAllDataRequired(reportsCommonPermissionVO.isViewAll());
		reportsCommonPermission.setSetFilterRequired(reportsCommonPermissionVO.isSetFilter());
		reportsCommonPermission.setExportRequired(reportsCommonPermissionVO.isExport());
		return reportsCommonPermissionRepository.save(reportsCommonPermission);
	}

	private CalendarCommonPermission savedCommonCalendarPermission(
			CommonCalendarPermissionVO commonCalendarPermissionVO) {
		CalendarCommonPermission calendarCommonPermission = new CalendarCommonPermission();
		calendarCommonPermission.setViewRequired(commonCalendarPermissionVO.isViewRequired());
		calendarCommonPermission.setAddRequired(commonCalendarPermissionVO.isAddRequired());
		calendarCommonPermission.setEditRequired(commonCalendarPermissionVO.isEditRequired());
		calendarCommonPermission.setDeleteRequired(commonCalendarPermissionVO.isDeleteRequired());
		return calendarCommonPermissionRepository.save(calendarCommonPermission);
	}

	private AdministrationCommonPermission savedAdministrationCommonPermission(
			CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		AdministrationCommonPermission administrationCommonPermission = new AdministrationCommonPermission();
		administrationCommonPermission.setViewRequired(commonAdministrationPermissionVO.isView());
		administrationCommonPermission.setAddRequired(commonAdministrationPermissionVO.isAdd());
		administrationCommonPermission.setEditRequired(commonAdministrationPermissionVO.isEdit());
		administrationCommonPermission
				.setActivateOrDeactivateRequired(commonAdministrationPermissionVO.isActiveOrDeactive());
		administrationCommonPermission.setDeleteRequired(commonAdministrationPermissionVO.isDelete());
		return administrationCommonPermissionRepository.save(administrationCommonPermission);
	}

	@Override
	@Transactional
	public void updateRolesDetails(Long roleId, RolesVO rolesVO) {
		Roles roles = rolesRepository.findById(roleId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + roleId));

		roles.setRoleName(rolesVO.getRoleName());
		roles.setRoleDisplayName(rolesVO.getRoleDisplayName());
		roles.setSelectAll(rolesVO.isSelectAllPermissions());

		// Dashboard permission
		if (rolesVO.getDashboardPermissionVO() != null) {
			DashboardPermissionVO dashboardPermissionVO = rolesVO.getDashboardPermissionVO();
			DashboardPermission dashboardPermission = roles.getDashboardPermission();
			dashboardPermission.setDashboardPermissionId(dashboardPermissionVO.getDashboardPermissionId());
			dashboardPermission.setDashboardPermissionAll(dashboardPermissionVO.isDashboardPermissionAll());
			dashboardPermission.setEditDashboardRequired(dashboardPermissionVO.isEditDashboardRequired());
			dashboardPermission.setReadDashboardRequired(dashboardPermissionVO.isReadDashboardRequired());
			DashboardPermission saveDashboardPermission = dashboardPermissionRepository.save(dashboardPermission);
			roles.setDashboardPermission(saveDashboardPermission);
		}

		// RevenuePermission
		if (rolesVO.getRevenuePermissionVO() != null) {
			RevenuePermissionVO revenuePermissionVO = rolesVO.getRevenuePermissionVO();

			RevenuePermission revenuePermission = roles.getRevenuePermission();
			revenuePermission.setRevenuePermissionId(revenuePermissionVO.getRevenuePermissionId());
			revenuePermission.setRevenuePermissionAll(revenuePermissionVO.isRevenuePermissionAll());
			revenuePermission.setViewAllEntriesRequired(revenuePermissionVO.isViewAllEntries());

			if (revenuePermissionVO.getRollingRevenueEntryPermissionVO() != null) {
				RollingRevenueEntryPermissionVO revenueEntryPermissionVO = revenuePermissionVO
						.getRollingRevenueEntryPermissionVO();

				RollingRevenueEntryPermission revenueEntryPermission = revenuePermission
						.getRollingRevenueEntryPermission();
				revenueEntryPermission.setRollingrevenueEntryPermissionId(
						revenueEntryPermissionVO.getRollingrevenueEntryPermissionId());
				revenueEntryPermission.setRollingRevenueEntryPermissionAll(
						revenueEntryPermissionVO.isRollingrevenueEntryPermissionAll());
				revenueEntryPermission.setViewAllEntriesRequired(revenueEntryPermissionVO.isViewAllEntriesRequired());
				revenueEntryPermission.setAddRevenueEntryRequired(revenueEntryPermissionVO.isAddRevenueEntryRequired());
				revenueEntryPermission
						.setEditRevenueEntryRequired(revenueEntryPermissionVO.isEditRevenueEntryRequired());
				revenueEntryPermission
						.setDeleteRevenueEntryRequired(revenueEntryPermissionVO.isDeleteRevenueEntryRequired());
				revenueEntryPermission
						.setCopyRevenueEntryRequired(revenueEntryPermissionVO.isCopyRevenueEntryRequired());
				revenueEntryPermission
						.setSubmitRevenueEntryRequired(revenueEntryPermissionVO.isSubmitRevenueEntryRequired());
				revenueEntryPermission.setExportRequired(revenueEntryPermissionVO.isExportRequired());
				RollingRevenueEntryPermission savedRevenueEntryPermission = rollingRevenueEntryPermissionRepository
						.save(revenueEntryPermission);
				revenuePermission.setRollingRevenueEntryPermission(savedRevenueEntryPermission);
			}
			if (revenuePermissionVO.getInvoiceDataUploadPermissionVO() != null) {
				InvoiceDataUploadPermissionVO invoiceUploadPermissionVO = revenuePermissionVO
						.getInvoiceDataUploadPermissionVO();

				InvoiceDataUploadPermission dataUploadPermission = revenuePermission.getInvoiceDataUploadPermission();
				dataUploadPermission
						.setInvoiceDataUploadPermissionId(invoiceUploadPermissionVO.getInvoiceDataUploadPermissionId());
				dataUploadPermission.setInvoiceDataUploadPermissionAll(
						invoiceUploadPermissionVO.isInvoiceDataUploadPermissionAll());
				dataUploadPermission.setViewListRequired(invoiceUploadPermissionVO.isViewListRequired());
				dataUploadPermission.setUploadDataRequired(invoiceUploadPermissionVO.isUploadDataRequired());
				dataUploadPermission.setEditOrReupload(invoiceUploadPermissionVO.isEditableRequired());
				dataUploadPermission.setDeleteDataRequired(invoiceUploadPermissionVO.isDeleteRequired());
				InvoiceDataUploadPermission savedInvoiceData = invoiceDataUploadPermissionRepository
						.save(dataUploadPermission);
				revenuePermission.setInvoiceDataUploadPermission(savedInvoiceData);
			}
			if (revenuePermissionVO.getReviewandPublishPermissionVO() != null) {
				ReviewandPublishPermissionVO reviewandPublishPermissionVO = revenuePermissionVO
						.getReviewandPublishPermissionVO();

				ReviewandPublishPermission publishPermission = revenuePermission.getReviewandPublishPermission();
				publishPermission.setReviewandPublishPermissionId(
						reviewandPublishPermissionVO.getReviewandPublishPermissionId());
				publishPermission.setReviewAndPublishPermissionAll(
						reviewandPublishPermissionVO.isReviewandPublishPermissionAll());
				publishPermission.setViewRequired(reviewandPublishPermissionVO.isViewRequired());
				publishPermission.setReviewAndSubmitRequired(reviewandPublishPermissionVO.isReviewAndSubmitRequired());
				publishPermission.setSaveRequired(reviewandPublishPermissionVO.isSaveRequired());
				publishPermission.setCopyRequired(reviewandPublishPermissionVO.isCopyRequired());
				publishPermission.setEditRequired(reviewandPublishPermissionVO.isEditRequired());
				publishPermission.setPublishRequired(reviewandPublishPermissionVO.isPublishRequired());
				publishPermission.setSaveRecipientsRequired(reviewandPublishPermissionVO.isSaveRecipientsRequired());
				ReviewandPublishPermission savedPublishPermission = reviewandPublishPermissionRepository
						.save(publishPermission);
				revenuePermission.setReviewandPublishPermission(savedPublishPermission);
			}
			RevenuePermission saveRevenuePermission = revenuePermissionRepository.save(revenuePermission);
			roles.setRevenuePermission(saveRevenuePermission);
		}

		// ReportsPermission
		if (rolesVO.getReportsPermissionVO() != null) {
			ReportsPermissionVO reportsPermissionVO = rolesVO.getReportsPermissionVO();

			ReportsPermission reportsPermission = roles.getReportsPermission();
			reportsPermission.setReportsPermissionId(reportsPermissionVO.getReportsPermissionId());
			reportsPermission.setReportsPermissionAll(reportsPermissionVO.isReportsPermissionsAll());
			reportsPermission.setViewAllEntriesRequired(reportsPermissionVO.isViewAllEntries());
			if (reportsPermissionVO.getBusinessTypeViewPermissionVO() != null) {
				BusinessTypeViewPermissionVO businessTypeViewPermissionVO = reportsPermissionVO
						.getBusinessTypeViewPermissionVO();

				BusinessTypeViewPermission businessTypeViewPermission = reportsPermission
						.getBusinessTypeViewPermission();
				businessTypeViewPermission.setBusinessTypeViewPermissionId(
						businessTypeViewPermissionVO.getBusinessTypeViewPermissionId());
				businessTypeViewPermission.setBusinessTypeViewPermissionAll(
						businessTypeViewPermissionVO.isBusinessTypeViewPermissionAll());
				if (businessTypeViewPermissionVO.getReportsCommonPermissionVO() != null) {
					ReportsCommonPermissionVO reportsCommonPermissionVO = businessTypeViewPermissionVO
							.getReportsCommonPermissionVO();
					businessTypeViewPermission.setReportsCommonPermission(updateReportsCommonPermissionDetails(
							businessTypeViewPermission.getReportsCommonPermission(), reportsCommonPermissionVO));
				}
				BusinessTypeViewPermission savedBusinessTypeView = businessTypeViewPermissionRepository
						.save(businessTypeViewPermission);
				reportsPermission.setBusinessTypeViewPermission(savedBusinessTypeView);
			}
			if (reportsPermissionVO.getSbuClientViewPermissionVO() != null) {
				SBUClientViewPermissionVO sbuClientViewPermissionVO = reportsPermissionVO
						.getSbuClientViewPermissionVO();

				SBUClientViewPermission sbuClientViewPermission = reportsPermission.getSbuClientViewPermission();
				sbuClientViewPermission
						.setSbuClientViewPermissionId(sbuClientViewPermissionVO.getSbuClientViewPermissionId());
				sbuClientViewPermission
						.setSbuClientViewPermissionAll(sbuClientViewPermissionVO.isSbuClientViewPermissionAll());
				if (sbuClientViewPermissionVO.getReportsCommonPermissionVO() != null) {
					ReportsCommonPermissionVO reportsCommonPermissionVO = sbuClientViewPermissionVO
							.getReportsCommonPermissionVO();
					sbuClientViewPermission.setReportsCommonPermission(updateReportsCommonPermissionDetails(
							sbuClientViewPermission.getReportsCommonPermission(), reportsCommonPermissionVO));
				}
				SBUClientViewPermission savedSBUClientViewPermission = sbuClientViewPermissionRepository
						.save(sbuClientViewPermission);
				reportsPermission.setSbuClientViewPermission(savedSBUClientViewPermission);
			}
			if (reportsPermissionVO.getProbabilityTypePermissionVO() != null) {
				ProbabilityTypePermissionVO probabilityTypePermissionVO = reportsPermissionVO
						.getProbabilityTypePermissionVO();

				ProbabilityTypeViewPermission probabilityTypeViewPermission = reportsPermission
						.getProbabilityTypeViewPermission();
				probabilityTypeViewPermission.setProbabilityTypeViewPermissionId(
						probabilityTypePermissionVO.getProbabilityTypePermissionId());
				probabilityTypeViewPermission.setProbabilityTypeViewPermissionAll(
						probabilityTypePermissionVO.isProbabilityTypePermissionAll());
				if (probabilityTypePermissionVO.getReportsCommonPermissionVO() != null) {
					ReportsCommonPermissionVO reportsCommonPermissionVO = probabilityTypePermissionVO
							.getReportsCommonPermissionVO();
					probabilityTypeViewPermission.setReportsCommonPermission(updateReportsCommonPermissionDetails(
							probabilityTypeViewPermission.getReportsCommonPermission(), reportsCommonPermissionVO));
				}
				ProbabilityTypeViewPermission savedProbabilityTypeView = probabilityTypeViewPermissionRepository
						.save(probabilityTypeViewPermission);
				reportsPermission.setProbabilityTypeViewPermission(savedProbabilityTypeView);
			}
			if (reportsPermissionVO.getRegionViewPermissionVO() != null) {
				RegionViewPermissionVO regionViewPermissionVO = reportsPermissionVO.getRegionViewPermissionVO();

				RegionWiseViewPermission regionWiseViewPermission = reportsPermission.getRegionWiseViewPermission();
				regionWiseViewPermission
						.setRegionWiseViewPermissionId(regionViewPermissionVO.getRegionViewPermissionId());
				regionWiseViewPermission
						.setRegionWiseViewPermissionAll(regionViewPermissionVO.isRegionViewPermissionAll());
				if (regionViewPermissionVO.getReportsCommonPermissionVO() != null) {
					ReportsCommonPermissionVO reportsCommonPermissionVO = regionViewPermissionVO
							.getReportsCommonPermissionVO();
					regionWiseViewPermission.setReportsCommonPermission(updateReportsCommonPermissionDetails(
							regionWiseViewPermission.getReportsCommonPermission(), reportsCommonPermissionVO));
				}
				RegionWiseViewPermission savedRegionWiseView = regionWiseViewPermissionRepository
						.save(regionWiseViewPermission);
				reportsPermission.setRegionWiseViewPermission(savedRegionWiseView);
			}
			if (reportsPermissionVO.getBuViewPermissionVO() != null) {
				BusinessUnitViewPermissionVO buViewPermissionVO = reportsPermissionVO.getBuViewPermissionVO();

				BusinessUnitWiseViewPermission businessUnitWiseViewPermission = reportsPermission
						.getBusinessUnitWiseViewPermission();
				businessUnitWiseViewPermission
						.setBusinessUnitWiseViewPermissionId(buViewPermissionVO.getBusinessUnitViewPermissionId());
				businessUnitWiseViewPermission
						.setBusinessUnitWiseViewPermissionAll(buViewPermissionVO.isBusinessUnitViewPermissionAll());
				if (buViewPermissionVO.getReportsCommonPermissionVO() != null) {
					ReportsCommonPermissionVO reportsCommonPermissionVO = buViewPermissionVO
							.getReportsCommonPermissionVO();
					businessUnitWiseViewPermission.setReportsCommonPermission(updateReportsCommonPermissionDetails(
							businessUnitWiseViewPermission.getReportsCommonPermission(), reportsCommonPermissionVO));
				}
				BusinessUnitWiseViewPermission savedBUViewPermission = businessUnitWiseViewPermissionRepository
						.save(businessUnitWiseViewPermission);
				reportsPermission.setBusinessUnitWiseViewPermission(savedBUViewPermission);
			}
			if (reportsPermissionVO.getClientWisePermissionVO() != null) {
				ClientWisePermissionVO clientWisePermissionVO = reportsPermissionVO.getClientWisePermissionVO();

				ClientWiseViewPermission clientWiseViewPermission = reportsPermission.getClientWiseViewPermission();
				clientWiseViewPermission
						.setClientWiseViewPermissionId(clientWisePermissionVO.getClientWisePermissionId());
				clientWiseViewPermission
						.setClientWiseViewPermissionAll(clientWisePermissionVO.isClientWisePermissionAll());
				if (clientWisePermissionVO.getReportsCommonPermissionVO() != null) {
					ReportsCommonPermissionVO reportsCommonPermissionVO = clientWisePermissionVO
							.getReportsCommonPermissionVO();
					clientWiseViewPermission.setReportsCommonPermission(updateReportsCommonPermissionDetails(
							clientWiseViewPermission.getReportsCommonPermission(), reportsCommonPermissionVO));
				}
				ClientWiseViewPermission savedClientWiseView = clientWiseViewPermissionRepository
						.save(clientWiseViewPermission);
				reportsPermission.setClientWiseViewPermission(savedClientWiseView);
			}
			if (reportsPermissionVO.getArchiveWisePermissionVO() != null) {
				ArchiveWisePermissionVO archiveWisePermissionVO = reportsPermissionVO.getArchiveWisePermissionVO();

				ArchiveWisePermission archiveWisePermission = reportsPermission.getArchiveWisePermission();
				archiveWisePermission.setArchiveWisePermissionId(archiveWisePermissionVO.getArchiveWisePermissionId());
				archiveWisePermission.setArchiveWisePermissionAll(archiveWisePermissionVO.isArchiveWisePermissionAll());
				if (archiveWisePermissionVO.getReportsCommonPermissionVO() != null) {
					ReportsCommonPermissionVO reportsCommonPermissionVO = archiveWisePermissionVO
							.getReportsCommonPermissionVO();
					archiveWisePermission.setReportsCommonPermission(updateReportsCommonPermissionDetails(
							archiveWisePermission.getReportsCommonPermission(), reportsCommonPermissionVO));
				}
				ArchiveWisePermission savedArchiveWise = archiveWisePermissionRepository.save(archiveWisePermission);
				reportsPermission.setArchiveWisePermission(savedArchiveWise);
			}
			ReportsPermission savedReportsPermission = reportsPermissionRepository.save(reportsPermission);
			roles.setReportsPermission(savedReportsPermission);
		}

		// Settings Permission
		if (rolesVO.getSettingsPermissionVO() != null) {
			SettingsPermissionVO settingsPermissionVO = rolesVO.getSettingsPermissionVO();

			SettingsPermission settingsPermission = roles.getSettingsPermission();
			settingsPermission.setSettingsPermissionId(settingsPermissionVO.getSettingsPermissionId());
			settingsPermission.setSettingsPermissionAll(settingsPermissionVO.isSettingsPermissionAll());
			settingsPermission.setViewAllEntriesRequired(settingsPermissionVO.isViewAll());
			if (settingsPermissionVO.getRolesPermissionVO() != null) {
				RolesPermissionVO rolesPermissionVO = settingsPermissionVO.getRolesPermissionVO();

				RolesPermission rolesPermission = settingsPermission.getRolesPermission();
				rolesPermission.setRolesPermissionId(rolesPermissionVO.getRolesPermissionId());
				rolesPermission.setRolesPermissionAll(rolesPermissionVO.isRolesPermissionAll());
				rolesPermission.setDeleteRequired(rolesPermissionVO.isDeleteRequired());
				rolesPermission.setCreateRoleRequired(rolesPermissionVO.isCreateRoleRequired());
				rolesPermission.setEditRoleRequired(rolesPermissionVO.isEditRoleRequired());
				RolesPermission savedRolesPermission = rolesPermissionRepository.save(rolesPermission);
				settingsPermission.setRolesPermission(savedRolesPermission);
			}
			if (settingsPermissionVO.getRoleUserAssignmentPermissionVO() != null) {
				RoleUserAssignmentPermissionVO userAssignmentPermissionVO = settingsPermissionVO
						.getRoleUserAssignmentPermissionVO();

				RoleUserPermission roleUserPermission = settingsPermission.getRoleUserPermission();
				roleUserPermission
						.setRoleUserPermissionId(userAssignmentPermissionVO.getRoleUserAssignmentPermissionId());
				roleUserPermission
						.setRoleUserPermissionAll(userAssignmentPermissionVO.isRoleUserAssignmentPermissionAll());

				SettingsCommonPermission settingsCommonPermission = roleUserPermission.getSettingsCommonPermission();
				settingsCommonPermission.setIsViewRequired(userAssignmentPermissionVO.isViewRequired());
				settingsCommonPermission.setIsAddRequired(userAssignmentPermissionVO.isCreateOrUploadRequired());
				settingsCommonPermission.setIsEditRequired(userAssignmentPermissionVO.isEditOrReUploadRequired());
				settingsCommonPermission.setIsDeleteRequired(userAssignmentPermissionVO.isDeleteRequired());
				SettingsCommonPermission savedSettingsCommonPermission = settingsCommonPermissionRepository
						.save(settingsCommonPermission);
				roleUserPermission.setSettingsCommonPermission(savedSettingsCommonPermission);
				RoleUserPermission savedRoleUserPermission = roleUserPermissionRepository.save(roleUserPermission);
				settingsPermission.setRoleUserPermission(savedRoleUserPermission);
			}
			if (settingsPermissionVO.getExplicitPermissionVO() != null) {
				ExplicitPermissionVO explicitPermissionVO = settingsPermissionVO.getExplicitPermissionVO();

				ExplicitPermission explicitPermission = settingsPermission.getExplicitPermission();
				explicitPermission.setExplicitPermissionAll(explicitPermissionVO.isExplicitPermissionAll());

				SettingsCommonPermission settingsCommonPermission = explicitPermission.getSettingsCommonPermission();
				settingsCommonPermission.setIsViewRequired(explicitPermissionVO.isViewRequired());
				settingsCommonPermission.setIsAddRequired(explicitPermissionVO.isCreateRequired());
				settingsCommonPermission.setIsEditRequired(explicitPermissionVO.isEditRequired());
				settingsCommonPermission.setIsDeleteRequired(explicitPermissionVO.isDeleteRequired());
				SettingsCommonPermission savedSettingsCommonPermission = settingsCommonPermissionRepository
						.save(settingsCommonPermission);
				explicitPermission.setSettingsCommonPermission(savedSettingsCommonPermission);
				ExplicitPermission savedExplicitPermission = explicitPermissionRepository.save(explicitPermission);
				settingsPermission.setExplicitPermission(savedExplicitPermission);
			}
			if (settingsPermissionVO.getAnnualTargetEntryPermissionVO() != null) {
				AnnualTargetEntryPermissionVO targetEntryPermissionVO = settingsPermissionVO
						.getAnnualTargetEntryPermissionVO();

				AnnualTargetEntryPermission targetEntryPermission = settingsPermission.getAnnualTargetEntryPermission();
				targetEntryPermission
						.setAnnualTargetEntryPermissionAll(targetEntryPermissionVO.isAnnualTargetEntryPermissionAll());
				targetEntryPermission.setViewRequired(targetEntryPermissionVO.isViewRequired());
				targetEntryPermission.setAddOrUploadRequired(targetEntryPermissionVO.isCreateRequired());
				targetEntryPermission.setReuploadOrEditRequired(targetEntryPermissionVO.isEditRequired());
				targetEntryPermission.setDeleteRequired(targetEntryPermissionVO.isDeleteRequired());
				AnnualTargetEntryPermission savedAnnualTargetEntryPermission = annualTargetEntryPermissionRepository
						.save(targetEntryPermission);
				settingsPermission.setAnnualTargetEntryPermission(savedAnnualTargetEntryPermission);
			}
			SettingsPermission savedSettingsPermission = settingsPermissionRepository.save(settingsPermission);
			roles.setSettingsPermission(savedSettingsPermission);
		}

		// Calendar Permissions
		if (rolesVO.getCalendarPermissionVO() != null) {
			CalendarPermissionVO calendarPermissionVO = rolesVO.getCalendarPermissionVO();

			CalendarPermission calendarPermission = roles.getCalendarPermission();
			calendarPermission.setCalendarPermissionAll(calendarPermissionVO.isCalendarPermissionAll());
			calendarPermission.setViewAllEntriesRequired(calendarPermissionVO.isViewAllCalendarPermission());
			if (calendarPermissionVO.getHolidayCalendarPermissionVO() != null) {
				HolidayCalendarPermissionVO holidayCalendarPermissionVO = calendarPermissionVO
						.getHolidayCalendarPermissionVO();

				HolidayCalendarPermission holidayCalendarPermission = calendarPermission.getHolidayCalendarPermission();
				holidayCalendarPermission
						.setHolidayCalendarPermissionAll(holidayCalendarPermissionVO.isHolidayCalendarPermissionAll());
				if (holidayCalendarPermissionVO.getCommonCalendarPermissionVO() != null) {
					CommonCalendarPermissionVO commonCalendarPermissionVO = holidayCalendarPermissionVO
							.getCommonCalendarPermissionVO();
					holidayCalendarPermission.setCalendarCommonPermission(UpdateCommonCalendarPermission(
							holidayCalendarPermission.getCalendarCommonPermission(), commonCalendarPermissionVO));
				}
				HolidayCalendarPermission savedHolidayCalendar = holidayCalendarPermissionRepository
						.save(holidayCalendarPermission);
				calendarPermission.setHolidayCalendarPermission(savedHolidayCalendar);
			}
			if (calendarPermissionVO.getFortnightlyMeetingPermissionVO() != null) {
				FortnightlyMeetingPermissionVO fortnightlyMeetingPermissionVO = calendarPermissionVO
						.getFortnightlyMeetingPermissionVO();

				FortnightlyMeetingsPermission meetingsPermission = calendarPermission
						.getFortnightlyMeetingsPermission();
				meetingsPermission.setFortnightlyMeetingsPermissionAll(
						fortnightlyMeetingPermissionVO.isFortnightlyMeetingPermissionAll());

				if (fortnightlyMeetingPermissionVO.getCommonCalendarPermissionVO() != null) {
					CommonCalendarPermissionVO commonCalendarPermissionVO = fortnightlyMeetingPermissionVO
							.getCommonCalendarPermissionVO();
					meetingsPermission.setCalendarCommonPermission(UpdateCommonCalendarPermission(
							meetingsPermission.getCalendarCommonPermission(), commonCalendarPermissionVO));
				}
				FortnightlyMeetingsPermission savedFortnightlyMeetings = fortnightlyMeetingsPermissionRepository
						.save(meetingsPermission);
				calendarPermission.setFortnightlyMeetingsPermission(savedFortnightlyMeetings);
			}
			if (calendarPermissionVO.getBdmMeetingPermissionVO() != null) {
				BDMMeetingPermissionVO bdmMeetingPermissionVO = calendarPermissionVO.getBdmMeetingPermissionVO();

				BDMMeetingsPermission bdmMeetingsPermission = calendarPermission.getBdmMeetingsPermission();
				bdmMeetingsPermission.setBdmMeetingsPermissionAll(bdmMeetingPermissionVO.isBdmMeetingPermissionAll());
				if (bdmMeetingPermissionVO.getCommonCalendarPermissionVO() != null) {
					CommonCalendarPermissionVO commonCalendarPermissionVO = bdmMeetingPermissionVO
							.getCommonCalendarPermissionVO();
					bdmMeetingsPermission.setCalendarCommonPermission(UpdateCommonCalendarPermission(
							bdmMeetingsPermission.getCalendarCommonPermission(), commonCalendarPermissionVO));
				}
				BDMMeetingsPermission savedBDMMeetingsPermission = bdmMeetingsPermissionRepository
						.save(bdmMeetingsPermission);
				calendarPermission.setBdmMeetingsPermission(savedBDMMeetingsPermission);
			}
			CalendarPermission savedCalendarPermission = calendarPermissionRepository.save(calendarPermission);
			roles.setCalendarPermission(savedCalendarPermission);
		}

		// Administration Permissions
		if (rolesVO.getAdministrationPermissionVO() != null) {
			AdministrationPermissionVO administrationPermissionVO = rolesVO.getAdministrationPermissionVO();

			AdministrationPermission administrationPermission = roles.getAdministrationPermission();
			administrationPermission
					.setAdministrationPermissionAll(administrationPermissionVO.isAdministrationPermissionAll());
			administrationPermission
					.setViewAllEntriesRequired(administrationPermissionVO.isViewAllAdministrationPermission());
			if (administrationPermissionVO.getAccountPermissionVO() != null) {
				AccountPermissionVO accountPermissionVO = administrationPermissionVO.getAccountPermissionVO();

				AccountPermission accountPermission = administrationPermission.getAccountPermission();
				accountPermission.setAccountPermissionAll(accountPermissionVO.isAccountPermissionAll());
				if (accountPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = accountPermissionVO
							.getCommonAdministrationPermissionVO();
					accountPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							accountPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				AccountPermission savedAccountPermission = accountPermissionRepository.save(accountPermission);
				administrationPermission.setAccountPermission(savedAccountPermission);
			}
			if (administrationPermissionVO.getOpportunityPermissionVO() != null) {
				OpportunityPermissionVO opportunityPermissionVO = administrationPermissionVO
						.getOpportunityPermissionVO();

				OpportunityPermission opportunityPermission = administrationPermission.getOpportunityPermission();
				opportunityPermission.setOpportunityPermissionAll(opportunityPermissionVO.isOpportunityPermissionAll());
				if (opportunityPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = opportunityPermissionVO
							.getCommonAdministrationPermissionVO();
					opportunityPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							opportunityPermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				OpportunityPermission savedOpportunityPermission = opportunityPermissionRepository
						.save(opportunityPermission);
				administrationPermission.setOpportunityPermission(savedOpportunityPermission);
			}
			if (administrationPermissionVO.getBusinessUnitPermissionVO() != null) {
				BusinessUnitPermissionVO businessUnitPermissionVO = administrationPermissionVO
						.getBusinessUnitPermissionVO();

				BusinessUnitPermission businessUnitPermission = administrationPermission.getBusinessUnitPermission();
				businessUnitPermission
						.setBusinessUnitPermissionAll(businessUnitPermissionVO.isBusinessUnitPermissionAll());
				if (businessUnitPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = businessUnitPermissionVO
							.getCommonAdministrationPermissionVO();
					businessUnitPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							businessUnitPermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				BusinessUnitPermission savedBusinessUnitPermission = businessUnitPermissionRepository
						.save(businessUnitPermission);
				administrationPermission.setBusinessUnitPermission(savedBusinessUnitPermission);
			}
			if (administrationPermissionVO.getRegionPermissionVO() != null) {
				RegionPermissionVO regionPermissionVO = administrationPermissionVO.getRegionPermissionVO();

				RegionPermission regionPermission = administrationPermission.getRegionPermission();
				regionPermission.setRegionPermissionAll(regionPermissionVO.isRegionPermissionAll());
				if (regionPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = regionPermissionVO
							.getCommonAdministrationPermissionVO();
					regionPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							regionPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				RegionPermission savedRegionPermission = regionPermissionRepository.save(regionPermission);
				administrationPermission.setRegionPermission(savedRegionPermission);
			}
			if (administrationPermissionVO.getSbuPermissionVO() != null) {
				SBUPermissionVO sbuPermissionVO = administrationPermissionVO.getSbuPermissionVO();

				SBUPermission sbuPermission = administrationPermission.getSbuPermission();
				sbuPermission.setSbuPermissionAll(sbuPermissionVO.isSbuPermissionAll());
				if (sbuPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = sbuPermissionVO
							.getCommonAdministrationPermissionVO();
					sbuPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							sbuPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				SBUPermission savedSBUPermission = sbuPermissionRepository.save(sbuPermission);
				administrationPermission.setSbuPermission(savedSBUPermission);
			}
			if (administrationPermissionVO.getSbuHeadPermissionVO() != null) {
				SBUHeadPermissionVO sbuHeadPermissionVO = administrationPermissionVO.getSbuHeadPermissionVO();

				SBUHeadPermission sbuHeadPermission = administrationPermission.getSbuHeadPermission();
				sbuHeadPermission.setSbuHeadPermissionAll(sbuHeadPermissionVO.isSbuHeadPermissionAll());
				if (sbuHeadPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = sbuHeadPermissionVO
							.getCommonAdministrationPermissionVO();
					sbuHeadPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							sbuHeadPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				SBUHeadPermission saveSBUHeadPermission = sbuHeadPermissionRepository.save(sbuHeadPermission);
				administrationPermission.setSbuHeadPermission(saveSBUHeadPermission);
			}
			if (administrationPermissionVO.getLocationPermissionVO() != null) {
				LocationPermissionVO locationPermissionVO = administrationPermissionVO.getLocationPermissionVO();

				LocationPermission locationPermission = administrationPermission.getLocationPermission();
				locationPermission.setLocationPermissionAll(locationPermissionVO.isLocationPermissionAll());
				if (locationPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = locationPermissionVO
							.getCommonAdministrationPermissionVO();
					locationPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							locationPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				LocationPermission savedLocationPermission = locationPermissionRepository.save(locationPermission);
				administrationPermission.setLocationPermission(savedLocationPermission);
			}
			if (administrationPermissionVO.getBdmPermissionVO() != null) {
				BDMPermissionVO bdmPermissionVO = administrationPermissionVO.getBdmPermissionVO();

				BDMPermission bdmPermission = administrationPermission.getBdmPermission();
				bdmPermission.setBdmPermissionAll(bdmPermissionVO.isBdmPermissionAll());
				if (bdmPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = bdmPermissionVO
							.getCommonAdministrationPermissionVO();
					bdmPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							bdmPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				BDMPermission savedBDMPermission = bdmPermissionRepository.save(bdmPermission);
				administrationPermission.setBdmPermission(savedBDMPermission);
			}
			if (administrationPermissionVO.getProbabilityPermissionVO() != null) {
				ProbabilityPermissionVO probabilityPermissionVO = administrationPermissionVO
						.getProbabilityPermissionVO();

				ProbabilityTypePermission probabilityTypePermission = administrationPermission
						.getProbabilityTypePermission();
				probabilityTypePermission
						.setProbabilityTypePermissionAll(probabilityPermissionVO.isProbabilityPermissionAll());
				if (probabilityPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = probabilityPermissionVO
							.getCommonAdministrationPermissionVO();
					probabilityTypePermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							probabilityTypePermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				ProbabilityTypePermission savedProbabilityTypePermission = probabilityTypePermissionRepository
						.save(probabilityTypePermission);
				administrationPermission.setProbabilityTypePermission(savedProbabilityTypePermission);
			}
			if (administrationPermissionVO.getBusinessTypePermissionVO() != null) {
				BusinessTypePermissionVO businessTypePermissionVO = administrationPermissionVO
						.getBusinessTypePermissionVO();

				BusinessTypePermission businessTypePermission = administrationPermission.getBusinessTypePermission();
				businessTypePermission
						.setBusinessTypePermissionAll(businessTypePermissionVO.isBusinessTypePermissionAll());
				if (businessTypePermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = businessTypePermissionVO
							.getCommonAdministrationPermissionVO();
					businessTypePermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							businessTypePermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				BusinessTypePermission savedBusinessTypePermission = businessTypePermissionRepository
						.save(businessTypePermission);
				administrationPermission.setBusinessTypePermission(savedBusinessTypePermission);
			}
			if (administrationPermissionVO.getCocPracticePermissionVO() != null) {
				CocPracticePermissionVO cocPracticePermissionVO = administrationPermissionVO
						.getCocPracticePermissionVO();

				COCPracticePermission cocPracticePermission = administrationPermission.getCocPracticePermission();
				cocPracticePermission.setCocPracticePermissionAll(cocPracticePermissionVO.isCocPracticePermissionAll());
				if (cocPracticePermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = cocPracticePermissionVO
							.getCommonAdministrationPermissionVO();
					cocPracticePermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							cocPracticePermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				COCPracticePermission savedCOCPracticePermission = cocPracticePermissionRepository
						.save(cocPracticePermission);
				administrationPermission.setCocPracticePermission(savedCOCPracticePermission);
			}
			if (administrationPermissionVO.getPricingTypePermissionVO() != null) {
				PricingTypePermissionVO pricingTypePermissionVO = administrationPermissionVO
						.getPricingTypePermissionVO();

				PricingTypePermission pricingTypePermission = administrationPermission.getPricingTypePermission();
				pricingTypePermission.setPricingTypePermissionAll(pricingTypePermissionVO.isPricingTypePermissionAll());
				if (pricingTypePermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = pricingTypePermissionVO
							.getCommonAdministrationPermissionVO();
					pricingTypePermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							pricingTypePermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				PricingTypePermission savedPricingTypePermission = pricingTypePermissionRepository
						.save(pricingTypePermission);
				administrationPermission.setPricingTypePermission(savedPricingTypePermission);
			}
			if (administrationPermissionVO.getWorkOrderPermissionVO() != null) {
				WorkOrderPermissionVO workOrderPermissionVO = administrationPermissionVO.getWorkOrderPermissionVO();

				WorkOrderPermission workOrderPermission = administrationPermission.getWorkOrderPermission();
				workOrderPermission.setWorkOrderPermissionAll(workOrderPermissionVO.isWorkOrderPermissionAll());
				if (workOrderPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = workOrderPermissionVO
							.getCommonAdministrationPermissionVO();
					workOrderPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							workOrderPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				WorkOrderPermission savedWorkOrderPermission = workOrderPermissionRepository.save(workOrderPermission);
				administrationPermission.setWorkOrderPermission(savedWorkOrderPermission);
			}
			if (administrationPermissionVO.getFinancialYearPermissionVO() != null) {
				FinancialYearPermissionVO financialYearPermissionVO = administrationPermissionVO
						.getFinancialYearPermissionVO();

				FinancialYearPermission financialYearPermission = administrationPermission.getFinancialYearPermission();
				financialYearPermission
						.setFinancialYearPermissionAll(financialYearPermissionVO.isFinancialYearPermissionAll());
				if (financialYearPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = financialYearPermissionVO
							.getCommonAdministrationPermissionVO();
					financialYearPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							financialYearPermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				FinancialYearPermission savedFinancialYearPermission = financialYearPermissionRepository
						.save(financialYearPermission);
				administrationPermission.setFinancialYearPermission(savedFinancialYearPermission);
			}
			if (administrationPermissionVO.getCurrencyPermissionVO() != null) {
				CurrencyPermissionVO currencyPermissionVO = administrationPermissionVO.getCurrencyPermissionVO();

				CurrencyPermission currencyPermission = administrationPermission.getCurrencyPermission();
				currencyPermission.setCurrencyPermissionAll(currencyPermissionVO.isCurrencyPermissionAll());
				if (currencyPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = currencyPermissionVO
							.getCommonAdministrationPermissionVO();
					currencyPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							currencyPermission.getAdministrationCommonPermission(), commonAdministrationPermissionVO));
				}
				CurrencyPermission savedCurrencyPermission = currencyPermissionRepository.save(currencyPermission);
				administrationPermission.setCurrencyPermission(savedCurrencyPermission);
			}
			if (administrationPermissionVO.getNotificationConfigPermissionVO() != null) {
				NotificationConfigPermissionVO notificationConfigPermissionVO = administrationPermissionVO
						.getNotificationConfigPermissionVO();

				NotificationConfigurationPermission configurationPermission = administrationPermission
						.getNotificationConfigurationPermission();
				configurationPermission.setNotificationConfigurationPermissionAll(
						notificationConfigPermissionVO.isNotificationConfigPermissionAll());
				if (notificationConfigPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = notificationConfigPermissionVO
							.getCommonAdministrationPermissionVO();
					configurationPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							configurationPermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				NotificationConfigurationPermission savedConfigurationPermission = notificationConfigurationPermissionRepository
						.save(configurationPermission);
				administrationPermission.setNotificationConfigurationPermission(savedConfigurationPermission);
			}
			if (administrationPermissionVO.getGlobalLeaveLassFactorPermissionVO() != null) {
				GlobalLeaveLassFactorPermissionVO globalLeaveLassFactorPermissionVO = administrationPermissionVO
						.getGlobalLeaveLassFactorPermissionVO();

				GlobalLeaveLossFactorPermission leaveLossFactorPermission = administrationPermission
						.getGlobalLeaveLossFactorPermission();
				leaveLossFactorPermission.setGlobalLeaveLossFactorPermissionAll(
						globalLeaveLassFactorPermissionVO.isGlobalLeaveLassFactorPermissionAll());
				if (globalLeaveLassFactorPermissionVO.getCommonAdministrationPermissionVO() != null) {
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = globalLeaveLassFactorPermissionVO
							.getCommonAdministrationPermissionVO();
					leaveLossFactorPermission.setAdministrationCommonPermission(updateAdministrationCommonPermission(
							leaveLossFactorPermission.getAdministrationCommonPermission(),
							commonAdministrationPermissionVO));
				}
				GlobalLeaveLossFactorPermission savedLeaveLossFactorPermission = globalLeaveLossFactorPermissionRepository
						.save(leaveLossFactorPermission);
				administrationPermission.setGlobalLeaveLossFactorPermission(savedLeaveLossFactorPermission);
			}
			AdministrationPermission savedAdministrationPermission = administrationPermissionRepository
					.save(administrationPermission);
			roles.setAdministrationPermission(savedAdministrationPermission);
		}
		rolesRepository.save(roles);
	}

	private ReportsCommonPermission updateReportsCommonPermissionDetails(
			ReportsCommonPermission reportsCommonPermission, ReportsCommonPermissionVO reportsCommonPermissionVO) {
		reportsCommonPermission.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
		reportsCommonPermission.setViewAllDataRequired(reportsCommonPermissionVO.isViewAll());
		reportsCommonPermission.setSetFilterRequired(reportsCommonPermissionVO.isSetFilter());
		reportsCommonPermission.setExportRequired(reportsCommonPermissionVO.isExport());
		return reportsCommonPermissionRepository.save(reportsCommonPermission);
	}

	private CalendarCommonPermission UpdateCommonCalendarPermission(CalendarCommonPermission calendarCommonPermission,
			CommonCalendarPermissionVO commonCalendarPermissionVO) {
		calendarCommonPermission
				.setCalendarCommonPermissionId(commonCalendarPermissionVO.getCommonCalendarPermissionId());
		calendarCommonPermission.setViewRequired(commonCalendarPermissionVO.isViewRequired());
		calendarCommonPermission.setAddRequired(commonCalendarPermissionVO.isAddRequired());
		calendarCommonPermission.setEditRequired(commonCalendarPermissionVO.isEditRequired());
		calendarCommonPermission.setDeleteRequired(commonCalendarPermissionVO.isDeleteRequired());
		return calendarCommonPermissionRepository.save(calendarCommonPermission);
	}

	private AdministrationCommonPermission updateAdministrationCommonPermission(
			AdministrationCommonPermission administrationCommonPermission,
			CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		administrationCommonPermission.setAdministrationCommonPermissionId(
				commonAdministrationPermissionVO.getCommonAdministrationPermissionId());
		administrationCommonPermission.setViewRequired(commonAdministrationPermissionVO.isView());
		administrationCommonPermission.setAddRequired(commonAdministrationPermissionVO.isAdd());
		administrationCommonPermission.setEditRequired(commonAdministrationPermissionVO.isEdit());
		administrationCommonPermission
				.setActivateOrDeactivateRequired(commonAdministrationPermissionVO.isActiveOrDeactive());
		administrationCommonPermission.setDeleteRequired(commonAdministrationPermissionVO.isDelete());
		return administrationCommonPermissionRepository.save(administrationCommonPermission);
	}

	@Override
	@Transactional
	public void deleteRolesById(Long roleId) {
		rolesRepository.findById(roleId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + roleId));
		rolesRepository.deleteById(roleId);
	}

	@Override
	public RolesVO getRolesById(Long roleId) {
		RolesVO rolesVO = new RolesVO();
		Roles roles = rolesRepository.findById(roleId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + roleId));
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
					ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
					ReportsCommonPermission reportsCommonPermission = businessTypeView.getReportsCommonPermission();
					reportsCommonPermissionVO
							.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
					reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
					reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
					reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
					reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
					businessTypeViewPermissionVO.setReportsCommonPermissionVO(reportsCommonPermissionVO);
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
					ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
					ReportsCommonPermission reportsCommonPermission = sbuClientView.getReportsCommonPermission();
					reportsCommonPermissionVO
							.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
					reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
					reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
					reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
					reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
					sbuClientViewPermissionVO.setReportsCommonPermissionVO(reportsCommonPermissionVO);
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
					ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
					ReportsCommonPermission reportsCommonPermission = probabilityType.getReportsCommonPermission();
					reportsCommonPermissionVO
							.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
					reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
					reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
					reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
					reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
					probabilityTypeVO.setReportsCommonPermissionVO(reportsCommonPermissionVO);
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
					ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
					ReportsCommonPermission reportsCommonPermission = regionWiseView.getReportsCommonPermission();
					reportsCommonPermissionVO
							.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
					reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
					reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
					reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
					reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
					regionViewPermissionVO.setReportsCommonPermissionVO(reportsCommonPermissionVO);
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
					ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
					ReportsCommonPermission reportsCommonPermission = businessUnitView.getReportsCommonPermission();
					reportsCommonPermissionVO
							.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
					reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
					reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
					reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
					reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
					businessUnitViewVO.setReportsCommonPermissionVO(reportsCommonPermissionVO);
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
					ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
					ReportsCommonPermission reportsCommonPermission = clientView.getReportsCommonPermission();
					reportsCommonPermissionVO
							.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
					reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
					reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
					reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
					reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
					clientWiseVO.setReportsCommonPermissionVO(reportsCommonPermissionVO);
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
					ReportsCommonPermissionVO reportsCommonPermissionVO = new ReportsCommonPermissionVO();
					ReportsCommonPermission reportsCommonPermission = archiveWise.getReportsCommonPermission();
					reportsCommonPermissionVO
							.setReportsCommonPermissionId(reportsCommonPermission.getReportsCommonPermissionId());
					reportsCommonPermissionVO.setViewAll(reportsCommonPermission.isViewAllDataRequired());
					reportsCommonPermissionVO.setSetFilter(reportsCommonPermission.isSetFilterRequired());
					reportsCommonPermissionVO.setExport(reportsCommonPermission.isExportRequired());
					reportsCommonPermissionVO.setActive(reportsCommonPermission.isActive());
					archiveWiseVO.setReportsCommonPermissionVO(reportsCommonPermissionVO);
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
					CommonCalendarPermissionVO calendarCommonPermissionVO = new CommonCalendarPermissionVO();
					CalendarCommonPermission calendarCommonPermission = holidayCalendarPermission
							.getCalendarCommonPermission();
					calendarCommonPermissionVO
							.setCommonCalendarPermissionId(calendarCommonPermission.getCalendarCommonPermissionId());
					calendarCommonPermissionVO.setViewRequired(calendarCommonPermission.isViewRequired());
					calendarCommonPermissionVO.setAddRequired(calendarCommonPermission.isAddRequired());
					calendarCommonPermissionVO.setEditRequired(calendarCommonPermission.isEditRequired());
					calendarCommonPermissionVO.setDeleteRequired(calendarCommonPermission.isDeleteRequired());
					holidayCalendarPermissionVO.setCommonCalendarPermissionVO(calendarCommonPermissionVO);
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
					CommonCalendarPermissionVO calendarCommonPermissionVO = new CommonCalendarPermissionVO();
					CalendarCommonPermission calendarCommonPermission = fortnightlyMeetingsPermission
							.getCalendarCommonPermission();
					calendarCommonPermissionVO
							.setCommonCalendarPermissionId(calendarCommonPermission.getCalendarCommonPermissionId());
					calendarCommonPermissionVO.setViewRequired(calendarCommonPermission.isViewRequired());
					calendarCommonPermissionVO.setAddRequired(calendarCommonPermission.isAddRequired());
					calendarCommonPermissionVO.setEditRequired(calendarCommonPermission.isEditRequired());
					calendarCommonPermissionVO.setDeleteRequired(calendarCommonPermission.isDeleteRequired());
					fortnightlyMeetingPermissionVO.setCommonCalendarPermissionVO(calendarCommonPermissionVO);
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
					CommonCalendarPermissionVO calendarCommonPermissionVO = new CommonCalendarPermissionVO();
					CalendarCommonPermission calendarCommonPermission = bdmMeetingsPermission
							.getCalendarCommonPermission();
					calendarCommonPermissionVO
							.setCommonCalendarPermissionId(calendarCommonPermission.getCalendarCommonPermissionId());
					calendarCommonPermissionVO.setViewRequired(calendarCommonPermission.isViewRequired());
					calendarCommonPermissionVO.setAddRequired(calendarCommonPermission.isAddRequired());
					calendarCommonPermissionVO.setEditRequired(calendarCommonPermission.isEditRequired());
					calendarCommonPermissionVO.setDeleteRequired(calendarCommonPermission.isDeleteRequired());
					bdmMeetingPermissionVO.setCommonCalendarPermissionVO(calendarCommonPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = accountPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					accountPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = opportunityPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					opportunityPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = businessUnitPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					businessUnitPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = regionPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					regionPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = sbuPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					sbuPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = sbuHeadPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					sbuHeadPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = locationPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					locationPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = bdmPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					bdmPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = probabilityTypePermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					probabilityPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = businessTypePermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					businessTypePermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = cocPracticePermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					cocPracticePermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = pricingTypePermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					pricingTypePermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = workOrderPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					workOrderPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = financialYearPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					financialYearPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = currencyPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					currencyPermissionVO.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = notificationConfigurationPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					commonAdministrationPermissionVO.setDelete(administrationCommonPermission.isDeleteRequired());
					notificationConfigPermissionVO
							.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
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
					CommonAdministrationPermissionVO commonAdministrationPermissionVO = new CommonAdministrationPermissionVO();
					AdministrationCommonPermission administrationCommonPermission = globalLeaveLossFactorPermission
							.getAdministrationCommonPermission();
					commonAdministrationPermissionVO.setCommonAdministrationPermissionId(
							administrationCommonPermission.getAdministrationCommonPermissionId());
					commonAdministrationPermissionVO.setView(administrationCommonPermission.isViewRequired());
					commonAdministrationPermissionVO.setAdd(administrationCommonPermission.isAddRequired());
					commonAdministrationPermissionVO.setEdit(administrationCommonPermission.isEditRequired());
					commonAdministrationPermissionVO
							.setActiveOrDeactive(administrationCommonPermission.isActivateOrDeactivateRequired());
					globalLeaveLossFactorPermissionVO
							.setCommonAdministrationPermissionVO(commonAdministrationPermissionVO);
				}
				administrationPermissionVO.setGlobalLeaveLassFactorPermissionVO(globalLeaveLossFactorPermissionVO);
			}
			rolesVO.setAdministrationPermissionVO(administrationPermissionVO);
		}
		return rolesVO;
	}
}
