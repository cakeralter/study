package cc.caker.study.spring.beans;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * PropertyValues
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = Lists.newArrayList();

    /**
     * 添加属性
     *
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        propertyValueList.add(pv);
    }

    /**
     * 获取属性值
     *
     * @return
     */
    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    /**
     * 获取属性值
     *
     * @param propertyName
     * @return
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
