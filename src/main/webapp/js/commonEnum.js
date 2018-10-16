(function() {
	/**
	 * ɾ�����
	 * 
	 * @author ��Ӱ��
	 * 
	 */
	DeletedFlag = {
		/**
		 * ��ɾ��
		 */
		YES : "1",
		/**
		 * δɾ��
		 */
		NO : "0",
	};

	/**
	 * �û���������
	 * 
	 * @author Mason_Ge
	 * 
	 */
	UserActionType = {
		/**
		 * ����
		 */
		SAVE : "save",
		/**
		 * �ύ
		 */
		SUBMIT : "submit",
		/**
		 * ���ύ
		 */
		DIS_SUBMIT : "disSubmit",
		/**
		 * ���
		 */
		APPROVE : "appr",
		/**
		 * �����
		 */
		DIS_APPROVE : "disAppr",
		/**
		 * ɾ��
		 */
		DEL : "del",
		/**
		 * ɾ���ָ�
		 */
		RE_DEL : "reDel",
	};

	/**
	 * һ��״̬
	 * 
	 * @author mason
	 * 
	 */
	CommonStatus = {
		/**
		 * �Ѵ���
		 */
		CREATED : "10",
		/**
		 * ���ύ
		 */
		SUBMITED : "20",
		/**
		 * �����
		 */
		APPROVED : "30",
		/**
		 * ��ɾ��
		 */
		DELETED : "90",
	};

	/**
	 * ���̲���
	 * 
	 * @author ��Ӱ��
	 * 
	 */
	WorkFlowAction = {
		/**
		 * ���̽�����
		 */
		PROCESSING : 'PROCESSING',
		/**
		 * ���̽ڵ�����ͨ��
		 */
		APPROVED : 'APPROVED',
		/**
		 * ���̽ڵ��������� �����̲��أ�
		 */
		REJECTED : 'REJECTED',
		/**
		 * ����������������
		 */
		FINISHED : 'FINISHED',
	};

	/**
	 * �Ƿ�����ö��ֵ
	 * 
	 * @author ��Ӱ��
	 * 
	 */
	EnabledFlag = {
		/**
		 * ����
		 */
		ENABLED : "1",
		/**
		 * ����
		 */
		DISABLED : "0",
	};
	/**
	 * �����ʶ
	 */
	LoanFlg = {
		/**
		 * ��
		 */
		YES : '1',
		/**
		 * ��
		 */
		NO : '0',
	};

})();