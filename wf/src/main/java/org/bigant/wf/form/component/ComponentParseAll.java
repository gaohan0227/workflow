package org.bigant.wf.form.component;

import org.bigant.wf.form.component.parse.*;

/**
 * 组建工具
 *
 * @author galen
 * @date 2024/1/3116:30
 */
public interface ComponentParseAll {

    ComponentParseText COMPONENT_PARSE_TEXT = new ComponentParseText();
    ComponentParseTextarea COMPONENT_PARSE_TEXTAREA = new ComponentParseTextarea();
    ComponentParseNumber COMPONENT_PARSE_NUMBER = new ComponentParseNumber();
    ComponentParseSelect COMPONENT_PARSE_SELECT = new ComponentParseSelect();
    ComponentParseMultiSelect COMPONENT_PARSE_MULTI_SELECT = new ComponentParseMultiSelect();
    ComponentParseDate COMPONENT_PARSE_DATE = new ComponentParseDate();
    ComponentParseDateRange COMPONENT_PARSE_DATE_RANGE = new ComponentParseDateRange();
    ComponentParseAttachment COMPONENT_PARSE_ATTACHMENT = new ComponentParseAttachment();

}