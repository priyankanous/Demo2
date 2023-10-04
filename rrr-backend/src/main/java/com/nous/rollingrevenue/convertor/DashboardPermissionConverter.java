//package com.nous.rollingrevenue.convertor;
//
//import org.springframework.stereotype.Component;
//
//import com.nous.rollingrevenue.model.DashboardPermission;
//import com.nous.rollingrevenue.vo.DashboardPermissionVO;
//
//@Component
//public class DashboardPermissionConverter {
//
//	public static DashboardPermission convertDashboardPermissionVOToDashboardPermission(
//			DashboardPermissionVO dashboardPermissionVO) {DashboardPermission dashboardPermission = new DashboardPermission();
//		if (dashboardPermissionVO != null) {
//			dashboardPermission.setDashboardPermissionId(dashboardPermissionVO.getDashboardPermissionId());
//			dashboardPermission.setIsEditDashboardRequired(dashboardPermissionVO.getIsEditDashboardRequired());
//			dashboardPermission.setIsReadDashboardRequired(dashboardPermissionVO.getIsReadDashboardRequired());
//		}
//		return dashboardPermission;
//	}
//
//	public static DashboardPermissionVO convertDashboardPermissionToDashboardPermissionVO(DashboardPermission dashboardPermission) {
//		DashboardPermissionVO dashboardPermissionVO = new DashboardPermissionVO();
//		if (dashboardPermission != null) {
//			dashboardPermissionVO.setDashboardPermissionId(dashboardPermission.getDashboardPermissionId());
//			dashboardPermissionVO.setIsEditDashboardRequired(dashboardPermission.getIsEditDashboardRequired());
//			dashboardPermissionVO.setIsReadDashboardRequired(dashboardPermission.getIsReadDashboardRequired());
//			dashboardPermissionVO.setActive(dashboardPermission.isActive());
//		}
//		return dashboardPermissionVO;
//	}
//}
