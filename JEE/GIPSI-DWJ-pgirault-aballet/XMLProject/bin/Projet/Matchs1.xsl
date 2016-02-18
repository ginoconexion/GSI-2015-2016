<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:t="http://fr.emac.gipsi/gsi/calendrier"
    exclude-result-prefixes="t"
    version="2.0">
    
    <xsl:output
        method="xml"
        version="1.0"
        encoding="UTF-8"
        indent="yes" />
    <xsl:template match="/">
    	<xsl:element name="resultats">
    		<xsl:for-each select="//t:groupe">
            	<xsl:apply-templates select="."></xsl:apply-templates>
            </xsl:for-each>
    	</xsl:element>
    </xsl:template>
    
    <xsl:template match="//t:groupe">
    	<xsl:for-each select="t:match">
            	<xsl:apply-templates select="."></xsl:apply-templates>
         </xsl:for-each>
    </xsl:template>
    
    <xsl:template match="//t:match">
    	<xsl:element name="match">
    		<xsl:attribute name="date"><xsl:value-of select="concat(substring(@date, 9, 2),'-', substring(@date, 6, 2),'-', substring(@date,1,4))"></xsl:value-of></xsl:attribute>
			<xsl:call-template name="equipe1"></xsl:call-template>
			<xsl:call-template name="equipe2"></xsl:call-template>
    	</xsl:element>
    </xsl:template>
    
    <xsl:template name="equipe1">
    	<xsl:element name="equipe">
			<xsl:attribute name="id"><xsl:value-of select="@equipe1"></xsl:value-of></xsl:attribute>
			<xsl:attribute name="score"><xsl:value-of select="@score1"></xsl:value-of></xsl:attribute>
			<xsl:variable name="numEquip" select="@equipe1"></xsl:variable>
			<xsl:apply-templates select="//t:position[@numEquipe = $numEquip]"></xsl:apply-templates>
		</xsl:element>
    </xsl:template>
    
    <xsl:template name="equipe2">
    	<xsl:element name="equipe">
			<xsl:attribute name="id"><xsl:value-of select="@equipe2"></xsl:value-of></xsl:attribute>
			<xsl:attribute name="score"><xsl:value-of select="@score2"></xsl:value-of></xsl:attribute>
			<xsl:variable name="numEquip" select="@equipe2"></xsl:variable>
			<xsl:apply-templates select="//t:position[@numEquipe = $numEquip]"></xsl:apply-templates>
		</xsl:element>
    </xsl:template>
    
    <xsl:template match="//t:position">
    	<xsl:element name="pays">
				<xsl:value-of select="../@id"></xsl:value-of>
			</xsl:element>
			<xsl:element name="poule">
				<xsl:value-of select="@numGroupe"></xsl:value-of>
			</xsl:element>
			<xsl:element name="drapeau">
				<xsl:attribute name="src"><xsl:value-of select="../@flag"></xsl:value-of></xsl:attribute>
			</xsl:element>
    </xsl:template>
</xsl:stylesheet>