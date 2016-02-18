<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:output
        method="xml"
        version="1.0"
        encoding="UTF-8"
        indent="yes" />
    <xsl:template match="/">
    	<xsl:element name="resultats">
	    	<xsl:for-each select="//match">
	    		<xsl:apply-templates select="."></xsl:apply-templates>
	    	</xsl:for-each>
    	</xsl:element>
    </xsl:template>
    
    <xsl:template match="//match">
    	<xsl:element name="match">
    		<xsl:attribute name="date"><xsl:value-of select="concat(substring(date, 9, 2),'-', substring(date, 6, 2),'-', substring(date,1,4))"></xsl:value-of></xsl:attribute>
			<xsl:call-template name="equipeDomicile"></xsl:call-template>
			<xsl:call-template name="equipeInvite"></xsl:call-template>
    	</xsl:element>
    </xsl:template>
    
    
    <xsl:template name="equipeDomicile">
    	<xsl:element name="equipe">
    		<xsl:variable name="numEquip" select="equipes/domicile"></xsl:variable>
			<xsl:attribute name="id"><xsl:value-of select="$numEquip"></xsl:value-of></xsl:attribute>
			<xsl:attribute name="score"><xsl:value-of select="substring(score, 1, 1)"></xsl:value-of></xsl:attribute>
			<xsl:apply-templates select="//position[@numEquipe = $numEquip]"></xsl:apply-templates>
		</xsl:element>
    </xsl:template>
    
    <xsl:template name="equipeInvite">
    	<xsl:element name="equipe">
    		<xsl:variable name="numEquip" select="equipes/invite"></xsl:variable>
			<xsl:attribute name="id"><xsl:value-of select="$numEquip"></xsl:value-of></xsl:attribute>
			<xsl:attribute name="score"><xsl:value-of select="substring(score, 3, 1)"></xsl:value-of></xsl:attribute>
			<xsl:apply-templates select="//position[@numEquipe = $numEquip]"></xsl:apply-templates>
		</xsl:element>
    </xsl:template>
    
    <xsl:template match="//position">
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