package com.jmc.scm.system.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jmc.scm.system.dao.SequenceDao;
import com.jmc.scm.system.dao.impl.SequenceDaoImpl;
import com.jmc.scm.system.model.SysSequence;
import com.jmc.scm.system.service.SequenceService;

@Service("sequenceServiceImpl")
public class SequenceServiceImpl implements SequenceService {

	public static final String BEAN_ID = "sequenceServiceImpl";

	@Autowired
	@Qualifier(SequenceDaoImpl.BEAN_ID)
	private SequenceDao sequenceDao;

	@Override
	public String generateSequence(String sequenceName) {
		SysSequence sequence = null;
		String formatedSequence = null, result, bufferString = "0";
		BigDecimal serialNo, serialMaxValue, buffer = new BigDecimal(1);
		StringBuffer sbSerial = new StringBuffer();
		int length;

		sequence = sequenceDao.findSequence(sequenceName);
		formatedSequence = this.analysisExpression(sequence
				.getSequenceExpression());

		length = sequence.getSerialLength().intValue();
		for (int i = 0; i < length; i++) {
			sbSerial.append(bufferString);
		}

		// 如果匹配一致，则继续增加流水号，如果匹配不一致，则更新业务表达式
		if (formatedSequence.equals(sequence.getExpressionFormat())) {
			serialNo = sequence.getSerialNo();

			serialMaxValue = sequence.getSerialMaxValue();
			serialNo = serialNo.add(buffer);

			if (serialNo.intValue() < serialMaxValue.intValue()) {
				sbSerial.append(serialNo.intValue());
				sbSerial.delete(0, sbSerial.length() - length);
				result = sbSerial.insert(0, formatedSequence).toString();

				sequence.setSerialNo(serialNo);
				this.updateSequence(sequence);
			} else {
				result = null;
			}
		} else {
			sbSerial.append(buffer.intValue());
			sbSerial.delete(0, 1);
			result = sbSerial.insert(0, formatedSequence).toString();

			sequence.setSerialNo(buffer);
			sequence.setExpressionFormat(formatedSequence);
			this.updateSequence(sequence);
		}

		return result;
	}

	protected String analysisExpression(String expression) {
		String year = "{#year}", yearMonth = "{#year_month}";
		String miniYear = "{#miniYear}", monthDay = "{#monthDay}";
		String day = "{#day}", date = "{#date}", buffer = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat();

		// 解析年份
		if (expression.indexOf(year) > -1) {
			format.applyPattern("yyyy");
			buffer = format.format(calendar.getTime());
			expression = expression.replace(year, buffer);
		}

		// 解析缩小年份，如2018解析为18
		if (expression.indexOf(miniYear) > -1) {
			format.applyPattern("yy");
			buffer = format.format(calendar.getTime());
			expression = expression.replace(miniYear, buffer);
		}

		// 解析年月
		if (expression.indexOf(yearMonth) > -1) {
			format.applyPattern("yyyyMM");
			buffer = format.format(calendar.getTime());
			expression = expression.replace(yearMonth, buffer);
		}

		// 解析月日
		if (expression.indexOf(monthDay) > -1) {
			format.applyPattern("MMdd");
			buffer = format.format(calendar.getTime());
			expression = expression.replace(monthDay, buffer);
		}

		// 解析年月日
		if (expression.indexOf(day) > -1) {
			format.applyPattern("yyyyMMdd");
			buffer = format.format(calendar.getTime());
			expression = expression.replace(day, buffer);
		}

		// 解析年月日时分秒
		if (expression.indexOf(date) > -1) {
			format.applyPattern("yyyyMMddHms");
			buffer = format.format(calendar.getTime());
			expression = expression.replace(date, buffer);
		}

		return expression;
	}

	@Override
	public void updateSequence(SysSequence sequence) {
		sequenceDao.updateSequnce(sequence);
	}

}
