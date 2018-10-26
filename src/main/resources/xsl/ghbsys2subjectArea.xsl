<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>

    <xsl:template match="/systematik">
        <subjectArea>
            <reference>
                <xsl:value-of select="@zahl"/>
            </reference>
            <xsl:call-template name="descriptions"/>
            <xsl:call-template name="comments"/>
            <xsl:call-template name="subCategorys"/>
            <xsl:call-template name="classifications"/>
        </subjectArea>
    </xsl:template>

    <xsl:template name="descriptions">
        <descriptions>
            <xsl:for-each select="bez">
                <description>
                    <text>
                        <xsl:value-of select="."/>
                    </text>
                    <target>
                        <xsl:value-of select="@campus"/>
                    </target>
                </description>
            </xsl:for-each>
        </descriptions>
    </xsl:template>

    <xsl:template match="@campus">
        <target>
            <xsl:value-of select="@campus"/>
        </target>
    </xsl:template>

    <xsl:template name="comments">
        <comments>
            <xsl:for-each select="comment">
                <comment>
                    <text>
                        <xsl:value-of select="."/>
                    </text>
                    <xsl:apply-templates select="@campus"/>
                </comment>
            </xsl:for-each>
        </comments>
    </xsl:template>

    <xsl:template name="subCategorys">
        <subcategorys>
            <xsl:for-each select="gruppe">
                <subCategory>
                    <reference>
                        <xsl:value-of select="concat(@von,'-',@bis)"/>
                    </reference>
                    <xsl:call-template name="descriptions"/>
                    <xsl:call-template name="comments"/>
                    <xsl:apply-templates select="@campus"/>
                    <xsl:call-template name="subCategorys"/>
                    <xsl:call-template name="classifications"/>
                </subCategory>
            </xsl:for-each>
        </subcategorys>
    </xsl:template>


    <xsl:template name="classifications">
        <classifications>
            <xsl:for-each select="stelle">
                <classification>
                    <reference>
                        <xsl:value-of select="@code"/>
                    </reference>
                    <target>
                        <xsl:value-of select="@campus"/>
                    </target>
                    <xsl:call-template name="descriptions"/>
                    <xsl:call-template name="subClassifications"/>
                </classification>
            </xsl:for-each>
        </classifications>
    </xsl:template>
    
    <xsl:template name="subClassifications">
        <subClassifications>
            <xsl:for-each select="b4">
                <subClassification>
                    <reference>
                        <xsl:value-of select="buchstabe/."/>
                    </reference>
                    <target>
                        <xsl:value-of select="@campus"/>
                    </target>
                    <xsl:call-template name="descriptions"/>
                </subClassification>
            </xsl:for-each>
        </subClassifications>
    </xsl:template>

</xsl:stylesheet>